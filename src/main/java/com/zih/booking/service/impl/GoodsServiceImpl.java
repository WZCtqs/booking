package com.zih.booking.service.impl;

import com.zih.booking.model.Goods;
import com.zih.booking.dao.GoodsMapper;
import com.zih.booking.service.GoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 货物信息表 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
