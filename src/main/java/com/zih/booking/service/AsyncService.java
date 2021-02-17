package com.zih.booking.service;

import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.BusiShippingorderGoods;

/**
 * 异步
 */
public interface AsyncService {
    void orderSendEmail(BusiShippingorder busiShippingorder, BusiShippingorderGoods busiShippingorderGoods);
}
