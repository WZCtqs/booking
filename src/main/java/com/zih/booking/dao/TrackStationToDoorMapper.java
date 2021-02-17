package com.zih.booking.dao;

import com.zih.booking.model.TrackStationToDoor;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运踪_去回程站到门/门到站 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-01-15
 */
@Repository
public interface TrackStationToDoorMapper extends BaseMapper<TrackStationToDoor> {
    List<Map<String,Object>> selectTripCabinet(String orderId);
    List<Map<String,Object>> selectTripAssembly(String orderId);
    List<Map<String,Object>> selectReturnCabinet(String orderId);
    List<Map<String,Object>> selectReturnAssembly(String orderId);
}
