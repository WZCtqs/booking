package com.zih.booking.response;

import com.zih.booking.model.BookingInquiry;
import com.zih.booking.model.BookingInquiryGoodsDetails;
import lombok.Data;

import java.util.List;

@Data
public class BookingInquiryResponse extends BookingInquiry {
    List<BookingInquiryGoodsDetails> goods;

    private String jobNumber;
}
