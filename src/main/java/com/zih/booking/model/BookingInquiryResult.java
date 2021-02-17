package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 询价反馈结果数据表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-28
 */
@TableName("booking_inquiry_result")
public class BookingInquiryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 询价反馈结果id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 询价id
     */
    @TableField("inquiry_id")
    private Integer inquiryId;
    /**
     * 上货站
     */
    @TableField("upload_station")
    private String uploadStation;
    /**
     * 下货站
     */
    @TableField("drop_station")
    private String dropStation;
    /**
     * 上货站备注
     */
    @TableField("upload_station_remark")
    private String uploadStationRemark;
    /**
     * 下货站备注
     */
    @TableField("drop_station_remark")
    private String dropStationRemark;
    /**
     * 提货地
     */
    @TableField("pick_up_address")
    private String pickUpAddress;
    /**
     * 提货距离
     */
    @TableField("pick_up_distance")
    private String pickUpDistance;
    /**
     * 提货时效
     */
    @TableField("pick_up_aging")
    private String pickUpAging;
    /**
     * 提货费
     */
    @TableField("pick_up_fees")
    private String pickUpFees;
    /**
     * 提货费币种
     */
    @TableField("pick_up_currency_type")
    private String pickUpCurrencyType;
    /**
     * 提货费有效期
     */
    @TableField("pick_up_expiration")
    private Date pickUpExpiration;
    /**
     * 提货备注
     */
    @TableField("pick_up_remark")
    private String pickUpRemark;
    /**
     * 提货报价反馈时间
     */
    @TableField("pick_up_feedback_time")
    private Date pickUpFeedbackTime;
    /**
     * 铁路运费
     */
    @TableField("railway_fees")
    private String railwayFees;
    /**
     * 铁路运费币种
     */
    @TableField("railway_currency_type")
    private String railwayCurrencyType;
    /**
     * 铁路收费标准
     */
    @TableField("railway_charges")
    private String railwayCharges;
    /**
     * 铁路费用有效期
     */
    @TableField("railway_expiration")
    private Date railwayExpiration;
    /**
     * 铁路时效
     */
    @TableField("railway_aging")
    private String railwayAging;
    /**
     * 铁路备注
     */
    @TableField("railway_remark")
    private String railwayRemark;
    /**
     * 铁路报价反馈时间
     */
    @TableField("railway_feedback_time")
    private Date railwayFeedbackTime;
    /**
     * 派送地
     */
    @TableField("delivery_address")
    private String deliveryAddress;
    /**
     * 派送距离
     */
    @TableField("delivery_distance")
    private String deliveryDistance;
    /**
     * 派送费用
     */
    @TableField("delivery_fees")
    private String deliveryFees;
    /**
     * 派送费用币种
     */
    @TableField("delivery_currency_type")
    private String deliveryCurrencyType;
    /**
     * 派送时效
     */
    @TableField("delivery_aging")
    private String deliveryAging;
    /**
     * 派送报价有效期
     */
    @TableField("delivery_expiration")
    private Date deliveryExpiration;
    /**
     * 派送备注
     */
    @TableField("delivery_remark")
    private String deliveryRemark;
    /**
     * 派送报价反馈时间
     */
    @TableField("delivery_feedback_time")
    private Date deliveryFeedbackTime;
    /**
     * 删除标识（0未删除；1已删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 确认标识（0未确认；1已确认）
     */
    @TableField("confirm_flag")
    private String confirmFlag;
    /**
     * 确认时间
     */
    @TableField("confirm_time")
    private Date confirmTime;
    /**
     * 确认人
     */
    @TableField("confirm_user")
    private Integer confirmUser;
    /**
     * 集疏备注
     */
    @TableField("js_remark")
    private String jsRemark;
    /**
     * 提箱费
     */
    @TableField("pick_up_box_fee")
    private String pickUpBoxFee;

    /**
     * 提箱费有效期
     */
    @TableField("pick_up_box_expiration")
    private Date pickUpBoxExpiration;
    /**
     * 还箱费
     */
    @TableField("return_box_fee")
    private String returnBoxFee;

    /**
     * 还箱费有效期
     */
    @TableField("return_box_expiration")
    private Date returnBoxExpiration;

    /**
     * 理货费
     */
    @TableField("lhu_fee")
    private String lhuFee;
    /**
     * 超长超重费
     */
    @TableField("cccz_fee")
    private String ccczFee;
    /**
     * 提空箱费
     */
    @TableField("tkx_fee")
    private String tkxFee;
    /**
     * 加固费
     */
    @TableField("jg_fee")
    private String jgFee;
    /**
     * 打托费
     */
    @TableField("dt_fee")
    private String dtFee;
    /**
     * 装拆箱费
     */
    @TableField("zcx_fee")
    private String zcxFee;

    /**
     * 集疏报价编码
     */
    @TableField("inquiry_number")
    private String inquiryNumber;

    /**
     * 集疏报价编码
     */
    @TableField("inquiry_num")
    private String inquiryNum;
    /**
     * 1（报价中）、2（待审核）、3（已报价）
     */
    @TableField("enquiry_state")
    private String enquiryState;
    /**
     * 还箱地
     */
    @TableField("hx_address")
    private String hxAddress;
    /**
     * 提箱地
     */
    @TableField("tx_address")
    private String txAddress;


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

    public String getUploadStation() {
        return uploadStation;
    }

    public void setUploadStation(String uploadStation) {
        this.uploadStation = uploadStation;
    }

    public String getDropStation() {
        return dropStation;
    }

    public void setDropStation(String dropStation) {
        this.dropStation = dropStation;
    }

    public String getUploadStationRemark() {
        return uploadStationRemark;
    }

    public void setUploadStationRemark(String uploadStationRemark) {
        this.uploadStationRemark = uploadStationRemark;
    }

    public String getDropStationRemark() {
        return dropStationRemark;
    }

    public void setDropStationRemark(String dropStationRemark) {
        this.dropStationRemark = dropStationRemark;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getPickUpDistance() {
        return pickUpDistance;
    }

    public void setPickUpDistance(String pickUpDistance) {
        this.pickUpDistance = pickUpDistance;
    }

    public String getPickUpAging() {
        return pickUpAging;
    }

    public void setPickUpAging(String pickUpAging) {
        this.pickUpAging = pickUpAging;
    }

    public String getPickUpFees() {
        return pickUpFees;
    }

    public void setPickUpFees(String pickUpFees) {
        this.pickUpFees = pickUpFees;
    }

    public String getPickUpCurrencyType() {
        return pickUpCurrencyType;
    }

    public void setPickUpCurrencyType(String pickUpCurrencyType) {
        this.pickUpCurrencyType = pickUpCurrencyType;
    }

    public Date getPickUpExpiration() {
        return pickUpExpiration;
    }

    public void setPickUpExpiration(Date pickUpExpiration) {
        this.pickUpExpiration = pickUpExpiration;
    }

    public String getPickUpRemark() {
        return pickUpRemark;
    }

    public void setPickUpRemark(String pickUpRemark) {
        this.pickUpRemark = pickUpRemark;
    }

    public Date getPickUpFeedbackTime() {
        return pickUpFeedbackTime;
    }

    public void setPickUpFeedbackTime(Date pickUpFeedbackTime) {
        this.pickUpFeedbackTime = pickUpFeedbackTime;
    }

    public String getRailwayFees() {
        return railwayFees;
    }

    public void setRailwayFees(String railwayFees) {
        this.railwayFees = railwayFees;
    }

    public String getRailwayCurrencyType() {
        return railwayCurrencyType;
    }

    public void setRailwayCurrencyType(String railwayCurrencyType) {
        this.railwayCurrencyType = railwayCurrencyType;
    }

    public String getRailwayCharges() {
        return railwayCharges;
    }

    public void setRailwayCharges(String railwayCharges) {
        this.railwayCharges = railwayCharges;
    }

    public Date getRailwayExpiration() {
        return railwayExpiration;
    }

    public void setRailwayExpiration(Date railwayExpiration) {
        this.railwayExpiration = railwayExpiration;
    }

    public String getRailwayAging() {
        return railwayAging;
    }

    public void setRailwayAging(String railwayAging) {
        this.railwayAging = railwayAging;
    }

    public String getRailwayRemark() {
        return railwayRemark;
    }

    public void setRailwayRemark(String railwayRemark) {
        this.railwayRemark = railwayRemark;
    }

    public Date getRailwayFeedbackTime() {
        return railwayFeedbackTime;
    }

    public void setRailwayFeedbackTime(Date railwayFeedbackTime) {
        this.railwayFeedbackTime = railwayFeedbackTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(String deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public String getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(String deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public String getDeliveryCurrencyType() {
        return deliveryCurrencyType;
    }

    public void setDeliveryCurrencyType(String deliveryCurrencyType) {
        this.deliveryCurrencyType = deliveryCurrencyType;
    }

    public String getDeliveryAging() {
        return deliveryAging;
    }

    public void setDeliveryAging(String deliveryAging) {
        this.deliveryAging = deliveryAging;
    }

    public Date getDeliveryExpiration() {
        return deliveryExpiration;
    }

    public void setDeliveryExpiration(Date deliveryExpiration) {
        this.deliveryExpiration = deliveryExpiration;
    }

    public String getDeliveryRemark() {
        return deliveryRemark;
    }

    public void setDeliveryRemark(String deliveryRemark) {
        this.deliveryRemark = deliveryRemark;
    }

    public Date getDeliveryFeedbackTime() {
        return deliveryFeedbackTime;
    }

    public void setDeliveryFeedbackTime(Date deliveryFeedbackTime) {
        this.deliveryFeedbackTime = deliveryFeedbackTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getConfirmUser() {
        return confirmUser;
    }

    public void setConfirmUser(Integer confirmUser) {
        this.confirmUser = confirmUser;
    }

    public String getJsRemark() {
        return jsRemark;
    }

    public void setJsRemark(String jsRemark) {
        this.jsRemark = jsRemark;
    }

    public String getReturnBoxFee() {
        return returnBoxFee;
    }

    public void setReturnBoxFee(String returnBoxFee) {
        this.returnBoxFee = returnBoxFee;
    }

    public String getLhuFee() {
        return lhuFee;
    }

    public void setLhuFee(String lhuFee) {
        this.lhuFee = lhuFee;
    }

    public String getCcczFee() {
        return ccczFee;
    }

    public void setCcczFee(String ccczFee) {
        this.ccczFee = ccczFee;
    }

    public String getTkxFee() {
        return tkxFee;
    }

    public void setTkxFee(String tkxFee) {
        this.tkxFee = tkxFee;
    }

    public String getJgFee() {
        return jgFee;
    }

    public void setJgFee(String jgFee) {
        this.jgFee = jgFee;
    }

    public String getDtFee() {
        return dtFee;
    }

    public void setDtFee(String dtFee) {
        this.dtFee = dtFee;
    }

    public String getZcxFee() {
        return zcxFee;
    }

    public void setZcxFee(String zcxFee) {
        this.zcxFee = zcxFee;
    }

    public String getInquiryNum() {
        return inquiryNum;
    }

    public void setInquiryNum(String inquiryNum) {
        this.inquiryNum = inquiryNum;
    }

    public String getEnquiryState() {
        return enquiryState;
    }

    public void setEnquiryState(String enquiryState) {
        this.enquiryState = enquiryState;
    }

    public String getHxAddress() {
        return hxAddress;
    }

    public void setHxAddress(String hxAddress) {
        this.hxAddress = hxAddress;
    }

    public String getTxAddress() {
        return txAddress;
    }

    public void setTxAddress(String txAddress) {
        this.txAddress = txAddress;
    }

    @Override
    public String toString() {
        return "BookingInquiryResult{" +
                "id=" + id +
                ", inquiryId=" + inquiryId +
                ", uploadStation=" + uploadStation +
                ", dropStation=" + dropStation +
                ", uploadStationRemark=" + uploadStationRemark +
                ", dropStationRemark=" + dropStationRemark +
                ", pickUpAddress=" + pickUpAddress +
                ", pickUpDistance=" + pickUpDistance +
                ", pickUpAging=" + pickUpAging +
                ", pickUpFees=" + pickUpFees +
                ", pickUpCurrencyType=" + pickUpCurrencyType +
                ", pickUpExpiration=" + pickUpExpiration +
                ", pickUpRemark=" + pickUpRemark +
                ", pickUpFeedbackTime=" + pickUpFeedbackTime +
                ", railwayFees=" + railwayFees +
                ", railwayCurrencyType=" + railwayCurrencyType +
                ", railwayCharges=" + railwayCharges +
                ", railwayExpiration=" + railwayExpiration +
                ", railwayAging=" + railwayAging +
                ", railwayRemark=" + railwayRemark +
                ", railwayFeedbackTime=" + railwayFeedbackTime +
                ", deliveryAddress=" + deliveryAddress +
                ", deliveryDistance=" + deliveryDistance +
                ", deliveryFees=" + deliveryFees +
                ", deliveryCurrencyType=" + deliveryCurrencyType +
                ", deliveryAging=" + deliveryAging +
                ", deliveryExpiration=" + deliveryExpiration +
                ", deliveryRemark=" + deliveryRemark +
                ", deliveryFeedbackTime=" + deliveryFeedbackTime +
                ", delFlag=" + delFlag +
                ", confirmFlag=" + confirmFlag +
                ", confirmTime=" + confirmTime +
                ", confirmUser=" + confirmUser +
                ", jsRemark=" + jsRemark +
                ", returnBoxFee=" + returnBoxFee +
                ", lhuFee=" + lhuFee +
                ", ccczFee=" + ccczFee +
                ", tkxFee=" + tkxFee +
                ", jgFee=" + jgFee +
                ", dtFee=" + dtFee +
                ", zcxFee=" + zcxFee +
                ", inquiryNum=" + inquiryNum +
                ", enquiryState=" + enquiryState +
                ", hxAddress=" + hxAddress +
                ", txAddress=" + txAddress +
                "}";
    }

    public String getPickUpBoxFee() {
        return pickUpBoxFee;
    }

    public void setPickUpBoxFee(String pickUpBoxFee) {
        this.pickUpBoxFee = pickUpBoxFee;
    }

    public String getInquiryNumber() {
        return inquiryNumber;
    }

    public void setInquiryNumber(String inquiryNumber) {
        this.inquiryNumber = inquiryNumber;
    }

    public Date getPickUpBoxExpiration() {
        return pickUpBoxExpiration;
    }

    public void setPickUpBoxExpiration(Date pickUpBoxExpiration) {
        this.pickUpBoxExpiration = pickUpBoxExpiration;
    }

    public Date getReturnBoxExpiration() {
        return returnBoxExpiration;
    }

    public void setReturnBoxExpiration(Date returnBoxExpiration) {
        this.returnBoxExpiration = returnBoxExpiration;
    }
}
