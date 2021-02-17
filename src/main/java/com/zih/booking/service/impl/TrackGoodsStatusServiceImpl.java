package com.zih.booking.service.impl;

import com.zih.booking.model.TrackGoodsStatus;
import com.zih.booking.dao.TrackGoodsStatusMapper;
import com.zih.booking.service.TrackGoodsStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运踪_货物状态表--以舱位号为单位，标记是否发车 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@Service
public class TrackGoodsStatusServiceImpl extends ServiceImpl<TrackGoodsStatusMapper, TrackGoodsStatus> implements TrackGoodsStatusService {

}
