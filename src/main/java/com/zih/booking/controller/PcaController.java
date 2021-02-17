package com.zih.booking.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.Area;
import com.zih.booking.model.City;
import com.zih.booking.model.Province;
import com.zih.booking.response.AreaVo;
import com.zih.booking.response.CityVo;
import com.zih.booking.response.ProvinceResponse;
import com.zih.booking.service.AreaService;
import com.zih.booking.service.CityService;
import com.zih.booking.service.ProvinceService;
import com.zih.booking.system.vo.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "省市区")
@RestController
@RequestMapping("/pca")
public class PcaController {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("list")
    public Result getPcaList(String language){
        if (StringUtils.isEmpty(language)) {
            language = "zh";
        }
        List<ProvinceResponse> provinceResponses = (List<ProvinceResponse>) redisTemplate.opsForList().range("PCA:" + language,0,-1);
        // 先获取redis内的省市区列表，没有再取数据库
        if(ObjectUtils.isEmpty(provinceResponses)) {
            List<Province> provinces =
                    provinceService.selectList(
                            new EntityWrapper<Province>().orderBy("province_code"));

            for (Province province : provinces) {
                ProvinceResponse provinceResponse = new ProvinceResponse();
                provinceResponse.setCode(province.getProvinceCode());
                if ("en".equals(language)) {
                    provinceResponse.setLabel(province.getProvinceNameEn());
                    provinceResponse.setValue(province.getProvinceNameEn());
                } else {
                    provinceResponse.setLabel(province.getProvinceName());
                    provinceResponse.setValue(province.getProvinceName());
                }
                List<City> cities =
                        cityService.selectList(new EntityWrapper<City>()
                                .eq("province_code", province.getProvinceCode())
                                .orderBy("city_code"));
                List<CityVo> cityVos = new ArrayList<>();
                for (City city : cities) {
                    CityVo cityVo = new CityVo();
                    cityVo.setCode(city.getCityCode());
                    if ("en".equals(language)) {
                        cityVo.setLabel(city.getCityNameEn());
                        cityVo.setValue(city.getCityNameEn());
                    } else {
                        cityVo.setLabel(city.getCityName());
                        cityVo.setValue(city.getCityName());
                    }
                    List<Area> areas =
                            areaService.selectList(new EntityWrapper<Area>()
                                    .eq("city_code", city.getCityCode())
                                    .orderBy("area_code"));
                    List<AreaVo> areaVos = new ArrayList<>();
                    for (Area area : areas) {
                        AreaVo areaVo = new AreaVo();
                        areaVo.setCode(area.getAreaCode());
                        if ("en".equals(language)) {
                            areaVo.setLabel(area.getAreaNameEn());
                            areaVo.setValue(area.getAreaNameEn());
                        } else {
                            areaVo.setLabel(area.getAreaName());
                            areaVo.setValue(area.getAreaName());
                        }
                        areaVos.add(areaVo);
                    }
                    cityVo.setChildren(areaVos);
                    cityVos.add(cityVo);
                }
                provinceResponse.setChildren(cityVos);
                provinceResponses.add(provinceResponse);
            }
            redisTemplate.opsForList().rightPushAll("PCA:" + language,provinceResponses);
        }
        return new Result<>(provinceResponses);
    }
}
