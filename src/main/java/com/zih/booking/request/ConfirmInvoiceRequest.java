package com.zih.booking.request;

import lombok.Data;

@Data
public class ConfirmInvoiceRequest  extends BaseRequest{
    private String orderId;
    private String taizang_id;
   // 人民币开票金额
    private String kp_rmb;
   // 美金开票金额
    private String  kp_usd;
   // 欧元开票金额
    private String  kp_eur;
  //  开票备注
   private String Remark_kp;
}
