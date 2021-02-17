package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订舱询盘表
 * </p>
 *
 * @author shahy123
 * @since 2020-01-11
 */
@TableName("booking_fuzzy_inquiry")
public class BookingFuzzyInquiry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 发货地城市code
     */
    @TableField("shipment_city_code")
    private String shipmentCityCode;
    /**
     * 发货地
     */
    @TableField("shipment_place")
    private String shipmentPlace;
    /**
     * 收货地城市code
     */
    @TableField("receipt_city_code")
    private String receiptCityCode;
    /**
     * 收货地
     */
    @TableField("receipt_place")
    private String receiptPlace;
    /**
     * 货物类型（0：整柜；1：拼箱）
     */
    @TableField("goods_type")
    private String goodsType;
    /**
     * 箱型
     */
    @TableField("container_type")
    private String containerType;
    /**
     * 箱量
     */
    @TableField("container_num")
    private Integer containerNum;
    /**
     * 包装方式
     */
    @TableField("package_type")
    private String packageType;
    /**
     * 是否可堆叠（0否：1是）
     */
    @TableField("is_stack")
    private String isStack;
    /**
     * 总数量
     */
    @TableField("total_amount")
    private String totalAmount;
    /**
     * 总重量(kg)
     */
    @TableField("total_weight")
    private String totalWeight;
    /**
     * 总体积(m³)
     */
    @TableField("total_volume")
    private String totalVolume;
    /**
     * 询盘时间
     */
    @TableField("inquiry_time")
    private Date inquiryTime;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 删除标识（0未删除；1：已删除）
     */
    @TableLogic
    @TableField("del_flag")
    private String delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipmentCityCode() {
        return shipmentCityCode;
    }

    public void setShipmentCityCode(String shipmentCityCode) {
        this.shipmentCityCode = shipmentCityCode;
    }

    public String getShipmentPlace() {
        return shipmentPlace;
    }

    public void setShipmentPlace(String shipmentPlace) {
        this.shipmentPlace = shipmentPlace;
    }

    public String getReceiptCityCode() {
        return receiptCityCode;
    }

    public void setReceiptCityCode(String receiptCityCode) {
        this.receiptCityCode = receiptCityCode;
    }

    public String getReceiptPlace() {
        return receiptPlace;
    }

    public void setReceiptPlace(String receiptPlace) {
        this.receiptPlace = receiptPlace;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public Integer getContainerNum() {
        return containerNum;
    }

    public void setContainerNum(Integer containerNum) {
        this.containerNum = containerNum;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getIsStack() {
        return isStack;
    }

    public void setIsStack(String isStack) {
        this.isStack = isStack;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Date getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(Date inquiryTime) {
        this.inquiryTime = inquiryTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BookingFuzzyInquiry{" +
        "id=" + id +
        ", shipmentCityCode=" + shipmentCityCode +
        ", shipmentPlace=" + shipmentPlace +
        ", receiptCityCode=" + receiptCityCode +
        ", receiptPlace=" + receiptPlace +
        ", goodsType=" + goodsType +
        ", containerType=" + containerType +
        ", containerNum=" + containerNum +
        ", packageType=" + packageType +
        ", isStack=" + isStack +
        ", totalAmount=" + totalAmount +
        ", totalWeight=" + totalWeight +
        ", totalVolume=" + totalVolume +
        ", inquiryTime=" + inquiryTime +
        ", clientId=" + clientId +
        ", delFlag=" + delFlag +
        "}";
    }
}
