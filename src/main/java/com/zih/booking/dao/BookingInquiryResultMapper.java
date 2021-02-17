package com.zih.booking.dao;

import com.zih.booking.model.BookingInquiryResult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 询价反馈结果数据表 Mapper 接口
 * </p>
 *
 * @author shahy123
 * @since 2020-01-19
 */
@Repository
public interface BookingInquiryResultMapper extends BaseMapper<BookingInquiryResult> {
    Map<String,Object> getPdf(String id);//询价结果导出pdf
}
