package com.zih.booking.dao;

import com.zih.booking.model.LadingBill;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-28
 */
@Repository
public interface LadingBillMapper extends BaseMapper<LadingBill> {
        String  getOrderIdByNumbers (String orderNumber);//order_id 王志超需要的
}
