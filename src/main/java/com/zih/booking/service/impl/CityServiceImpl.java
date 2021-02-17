package com.zih.booking.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zih.booking.dao.CityMapper;
import com.zih.booking.model.City;
import com.zih.booking.service.CityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author shahy123
 * @since 2019-12-25
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

}
