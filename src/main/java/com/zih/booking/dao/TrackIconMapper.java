package com.zih.booking.dao;

import com.zih.booking.model.TrackIcon;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.response.OneNodeResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 运踪图标表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-21
 */
@Repository
public interface TrackIconMapper extends BaseMapper<TrackIcon> {
  List<OneNodeResponse> selectEnOneNode(String  orderId);
  List<OneNodeResponse> selectZhOneNode(String  orderId);
}
