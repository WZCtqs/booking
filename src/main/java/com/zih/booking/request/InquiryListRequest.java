package com.zih.booking.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class InquiryListRequest extends BaseRequest {

    /**
     * 询盘日期
     */
    private String inquiryNum;//集疏报价编码
    private String inquiryNumber;//公路报价编码
    private String senderPlace;//发货地
    private String receiverPlace;//收货地
    private List<String> clientIds;
    private String inquiryDate;
    private String goodsType;
    private String eastOrWest;
    private String goodsName;
    /**
     * 询价状态,1（报价中）、2（待审核）、3（已报价）、4（驳回）
     */
    private String inquiryState;
    /**
     * 子账号编号
     */
    private String jobNumber;
    /**
     * 每页多少条数据
     */
    private Integer limit;

    /**
     * 当前页码
     */
    private Integer page;

}
