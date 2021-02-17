package com.zih.booking.dao;

import com.zih.booking.model.TrackTwoLevel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.response.OneNodeResponse;
import com.zih.booking.response.TwoNodeResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 运踪二级节点表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-21
 */
@Repository
public interface TrackTwoLevelMapper extends BaseMapper<TrackTwoLevel> {
    List<TwoNodeResponse> selectTwoNode(String  id);
}
