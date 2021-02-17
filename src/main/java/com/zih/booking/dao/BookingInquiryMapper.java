package com.zih.booking.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.BookingInquiry;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.request.InquiryListRequest;
import com.zih.booking.response.BookingInquiryResponse;
import com.zih.booking.vo.InquiryVo;
import com.zih.booking.vo.inquiryResultZx;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订舱询价表 Mapper 接口
 * </p>
 *
 * @author shahy123
 * @since 2020-01-19
 */
@Repository
public interface BookingInquiryMapper extends BaseMapper<BookingInquiry> {
            InquiryVo selectInquiryVo(String inquiryId);
    List<BookingInquiryResponse> selectList(Page page, InquiryListRequest inquiryListRequest);
    public inquiryResultZx inquiryPirce(String inquiryRecordId); //查询整柜询价信息
    public Double selectZgCount(String classId); //查询班列已定的整柜数量
    public Integer selectClassZgCount(String classId); //查询班列整柜总数
    public int updateInquiry(inquiryResultZx bookingInquiry); //更新箱量
    public int updateInquiryResult(inquiryResultZx bookingInquiry);//更新价格

}
