package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订舱询盘-结果反馈表
 * </p>
 *
 * @author shahy123
 * @since 2020-01-11
 */
@TableName("booking_fuzzy_inquiry_result")
public class BookingFuzzyInquiryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 询盘id
     */
    @TableField("inquiry_id")
    private Integer inquiryId;
    /**
     * 运输区间
     */
    private String interval;
    /**
     * 上/下货站
     */
    @TableField("upload_site")
    private String uploadSite;
    /**
     * 运输方式
     */
    @TableField("transport_mode")
    private String transportMode;
    /**
     * 时效
     */
    private String aging;
    /**
     * 费用
     */
    private String fees;
    /**
     * 费用币种
     */
    @TableField("currency_type")
    private String currencyType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getUploadSite() {
        return uploadSite;
    }

    public void setUploadSite(String uploadSite) {
        this.uploadSite = uploadSite;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getAging() {
        return aging;
    }

    public void setAging(String aging) {
        this.aging = aging;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "BookingFuzzyInquiryResult{" +
        "id=" + id +
        ", inquiryId=" + inquiryId +
        ", interval=" + interval +
        ", uploadSite=" + uploadSite +
        ", transportMode=" + transportMode +
        ", aging=" + aging +
        ", fees=" + fees +
        ", currencyType=" + currencyType +
        "}";
    }
}
