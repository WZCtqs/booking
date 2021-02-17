package com.zih.booking.dao;

import com.zih.booking.model.BusiSite;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.response.CityResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 城市站点表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@Repository
public interface BusiSiteMapper extends BaseMapper<BusiSite> {
    List<CityResponse> selectPXXList(@Param("lineTypeid")String lineTypeid,@Param("eastWest") Integer eastWest, @Param("language")String language);
    List<CityResponse> selectPXSList(@Param("lineTypeid")String lineTypeid,@Param("eastWest")Integer eastWest, @Param("language")String language);
    List<CityResponse> selectZXXList(@Param("lineTypeid")String lineTypeid,@Param("eastWest")Integer eastWest, @Param("language")String language );
    List<CityResponse> selectZXSList(@Param("lineTypeid")String lineTypeid,@Param("eastWest")Integer eastWest, @Param("language")String language);
}
