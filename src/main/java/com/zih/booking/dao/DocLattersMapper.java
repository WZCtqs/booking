package com.zih.booking.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.model.DocClientLatters;
import com.zih.booking.model.DocOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单—单证（订单所需单证）Mapper接口
 *
 * @author hp
 * @date 2020-04-13
 */
@Repository
public interface DocLattersMapper extends BaseMapper<DocClientLatters> {

    List<DocClientLatters> selectLattersByOrderId(String orderId);
}
