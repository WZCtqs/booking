package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订舱询价表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-14
 */
@TableName("booking_inquiry")
public class BookingInquiry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /** 发货地省份 */
    @TableField("sender_province")
    private String senderProvince;
    /** 发货地城市 */
    @TableField("sender_city")
    private String senderCity;
    /** 发货地区域 */
    @TableField("sender_area")
    private String senderArea;
    /**
     * 发货地
     */
    @TableField("shipment_place")
    private String shipmentPlace;
    /**
     * 上货站
     */
    @TableField("upload_station")
    private String uploadStation;
    /** 收货地省份 */
    @TableField("receive_province")
    private String receiveProvince;
    /** 收货地城市 */
    @TableField("receive_city")
    private String receiveCity;
    /** 收货地区域 */
    @TableField("receive_area")
    private String receiveArea;
    /**
     * 收货地
     */
    @TableField("receipt_place")
    private String receiptPlace;
    /**
     * 下货站
     */
    @TableField("drop_station")
    private String dropStation;
    /**
     * 服务（0：门到门；1：门到站；2：站到站；3：站到门）
     */
    @TableField("booking_service")
    private String bookingService;
    /**
     * 是否委托zih提货（0：是；1：否）
     */
    @TableField("is_pick_up")
    private String isPickUp;
    /**
     * 是否委托zih送货（0：是；1：否）
     */
    @TableField("is_delivery")
    private String isDelivery;
    /**
     * 是否委托zih代理报关（0：否；1：是）
     */
    @TableField("is_order_customs")
    private String isOrderCustoms;
    /**
     * 是否委托zih代理清关（0：否；1：是）
     */
    @TableField("is_clear_customs")
    private String isClearCustoms;
    /**
     * 箱属（0：自备；1：zih）
     */
    @TableField("container_belong")
    private String containerBelong;
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
     * 计费体积(m³)
     */
    @TableField("billable_volume")
    private String billableVolume;
    /**
     * 询价时间
     */
    @TableField("inquiry_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inquiryTime;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 删除标识（0未删除；1：已删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 线路线路类型（0郑欧（中欧）、2郑中亚（中亚）、3郑东盟(中越)）、4郑俄（中俄）
     */
    @TableField("line_type")
    private String lineType;
    /**
     * 提箱地
     */
    @TableField("tx_address")
    private String txAddress;
    /**
     * 还箱地
     */
    @TableField("hx_address")
    private String hxAddress;
    /**
     * 去回程,0为去程 1为回程
     */
    @TableField("east_or_west")
    private String eastOrWest;
    /**
     * 上货站编号
     */
    @TableField("upload_station_num")
    private String uploadStationNum;
    /**
     * 下货站编号
     */
    @TableField("drop_station_num")
    private String dropStationNum;
    /**
     * 备注
     */
    private String remark;
    /**
     * 国内公路运输车辆类型，0普通车、1普通卡车、2白卡专车
     */
    @TableField("truck_type")
    private String truckType;
    /**
     * 时效（箱型亚欧，0普通，1加急）
     */
    private String limitation;
    /**
     * 客户自送货方式（1整柜到堆场，0散货到堆场）
     */
    @TableField("delivery_self_type")
    private String deliverySelfType;
    /**
     * 提货方式（0整柜到堆场，1散货到堆场）整柜和站到站同时选择
     */
    @TableField("delivery_type")
    private String deliveryType;

    /**
     *  分拨方式（0整柜派送，1拆箱散货派送）
     */
    @TableField("distribution_type")
    private String distributionType;
    /**
     * 客户名称
     */
    @TableField("client_unit")
    private String clientUnit;
    /**
     * 是否易碎（1是0否）
     */
    @TableField("goods_fragile")
    private String goodsFragile;
    /**
     * 单件超长超重 1是0否
     */
    @TableField("goods_general")
    private String goodsGeneral;

    /**
     * 询价状态（报价完成/处理中）
     */
    @TableField("inquiry_state")
    private String inquiryState;

    /**
     * 驳回原因
     */
    @TableField("turndown_reason")
    private String turndownReason;

    /**
     * 托书id
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 报价有效期
     */
    @TableField("valid_date")
    private Date validDate;
    /**
     * 货物品名
     */
    @TableField(exist = false)
    private String goodsName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipmentPlace() {
        return shipmentPlace;
    }

    public void setShipmentPlace(String shipmentPlace) {
        this.shipmentPlace = shipmentPlace;
    }

    public String getUploadStation() {
        return uploadStation;
    }

    public void setUploadStation(String uploadStation) {
        this.uploadStation = uploadStation;
    }

    public String getReceiptPlace() {
        return receiptPlace;
    }

    public void setReceiptPlace(String receiptPlace) {
        this.receiptPlace = receiptPlace;
    }

    public String getDropStation() {
        return dropStation;
    }

    public void setDropStation(String dropStation) {
        this.dropStation = dropStation;
    }

    public String getBookingService() {
        return bookingService;
    }

    public void setBookingService(String bookingService) {
        this.bookingService = bookingService;
    }

    public String getIsPickUp() {
        return isPickUp;
    }

    public void setIsPickUp(String isPickUp) {
        this.isPickUp = isPickUp;
    }

    public String getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(String isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getIsOrderCustoms() {
        return isOrderCustoms;
    }

    public void setIsOrderCustoms(String isOrderCustoms) {
        this.isOrderCustoms = isOrderCustoms;
    }

    public String getIsClearCustoms() {
        return isClearCustoms;
    }

    public void setIsClearCustoms(String isClearCustoms) {
        this.isClearCustoms = isClearCustoms;
    }

    public String getContainerBelong() {
        return containerBelong;
    }

    public void setContainerBelong(String containerBelong) {
        this.containerBelong = containerBelong;
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

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getHxAddress() {
        return hxAddress;
    }

    public void setHxAddress(String hxAddress) {
        this.hxAddress = hxAddress;
    }

    public String getEastOrWest() {
        return eastOrWest;
    }

    public void setEastOrWest(String eastOrWest) {
        this.eastOrWest = eastOrWest;
    }

    public String getUploadStationNum() {
        return uploadStationNum;
    }

    public void setUploadStationNum(String uploadStationNum) {
        this.uploadStationNum = uploadStationNum;
    }

    public String getDropStationNum() {
        return dropStationNum;
    }

    public void setDropStationNum(String dropStationNum) {
        this.dropStationNum = dropStationNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getClientUnit() {
        return clientUnit;
    }

    public void setClientUnit(String clientUnit) {
        this.clientUnit = clientUnit;
    }

    public String getGoodsFragile() {
        return goodsFragile;
    }

    public void setGoodsFragile(String goodsFragile) {
        this.goodsFragile = goodsFragile;
    }

    public String getInquiryState() {
        return inquiryState;
    }

    public void setInquiryState(String inquiryState) {
        this.inquiryState = inquiryState;
    }

    @Override
    public String toString() {
        return "BookingInquiry{" +
                "id=" + id +
                ", shipmentPlace=" + shipmentPlace +
                ", uploadStation=" + uploadStation +
                ", receiptPlace=" + receiptPlace +
                ", dropStation=" + dropStation +
                ", bookingService=" + bookingService +
                ", isPickUp=" + isPickUp +
                ", isDelivery=" + isDelivery +
                ", isOrderCustoms=" + isOrderCustoms +
                ", isClearCustoms=" + isClearCustoms +
                ", containerBelong=" + containerBelong +
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
                ", lineType=" + lineType +
                ", hxAddress=" + hxAddress +
                ", eastOrWest=" + eastOrWest +
                ", uploadStationNum=" + uploadStationNum +
                ", dropStationNum=" + dropStationNum +
                ", remark=" + remark +
                ", truckType=" + truckType +
                ", limitation=" + limitation +
                ", deliveryType=" + deliveryType +
                ", clientUnit=" + clientUnit +
                ", goodsFragile=" + goodsFragile +
                ", inquiryState=" + inquiryState +
                "}";
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea;
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince;
    }
    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity;
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTurndownReason() {
        return turndownReason;
    }

    public void setTurndownReason(String turndownReason) {
        this.turndownReason = turndownReason;
    }

    public String getTxAddress() {
        return txAddress;
    }

    public void setTxAddress(String txAddress) {
        this.txAddress = txAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliverySelfType() {
        return deliverySelfType;
    }

    public void setDeliverySelfType(String deliverySelfType) {
        this.deliverySelfType = deliverySelfType;
    }

    public String getGoodsGeneral() {
        return goodsGeneral;
    }

    public void setGoodsGeneral(String goodsGeneral) {
        this.goodsGeneral = goodsGeneral;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getBillableVolume() {
        return billableVolume;
    }

    public void setBillableVolume(String billableVolume) {
        this.billableVolume = billableVolume;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }
}
