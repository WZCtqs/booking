package com.zih.booking.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.BookingInquiry;
import com.zih.booking.model.BusiClasses;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.vo.InquiryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 班列表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@Repository
public interface BusiClassesMapper extends BaseMapper<BusiClasses> {
    List<BusiClasses> selectZXList(  InquiryVo bookingInquiry);
    List<BusiClasses> selectPXList( InquiryVo bookingInquiry);
}
