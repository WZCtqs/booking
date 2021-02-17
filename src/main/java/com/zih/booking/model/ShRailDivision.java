package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 散货铁路运费
 * </p>
 *
 * @author shahy123
 * @since 2020-03-17
 */
@TableName("sh_rail_division")
public class ShRailDivision implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 线路类型（郑欧、郑中亚、郑俄、郑东盟）
     */
    @TableField("line_type")
    private String lineType;
    /**
     * 上货站点
     */
    @TableField("order_upload_site")
    private String orderUploadSite;
    /**
     * 下货站点
     */
    @TableField("order_unload_site")
    private String orderUnloadSite;
    /**
     * 散货运费单价
     */
    @TableField("lcl_freight")
    private String lclFreight;
    /**
     * 起运站费用（散货  USD/CBM或者（USD/SET））
     */
    @TableField("order_upload_site_cost")
    private BigDecimal orderUploadSiteCost;
    /**
     * 目的站基础费用（包含拆箱费、基本仓储费
     */
    @TableField("order_unload_site_bacost")
    private BigDecimal orderUnloadSiteBacost;
    /**
     * 最小体积
     */
    @TableField("min_volume")
    private String minVolume;
    /**
     * 最大体积(包含)
     */
    @TableField("max_volume")
    private String maxVolume;
    /**
     * 每票固定收费方数小于等于临界值费用（USD/SET
     */
    @TableField("min_volume_cost")
    private BigDecimal minVolumeCost;
    /**
     * 每票固定收费方数大于最小值不超过最大值费用（USD/SET）
     */
    @TableField("middle_volume_cost")
    private BigDecimal middleVolumeCost;
    /**
     * 每票固定收费方数大于临界最大值费用（USD/SET
     */
    @TableField("max_volume_cost")
    private BigDecimal maxVolumeCost;
    /**
     * 币种（运费单位）
     */
    private String unit;
    /**
     * 时效（站到站时间，单位）
     */
    @TableField("time_limit")
    private String timeLimit;
    /**
     * 有效开始日期
     */
    @TableField("valid_start_date")
    private Date validStartDate;
    /**
     * 有效结束日期
     */
    @TableField("valid_end_date")
    private Date validEndDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getOrderUploadSite() {
        return orderUploadSite;
    }

    public void setOrderUploadSite(String orderUploadSite) {
        this.orderUploadSite = orderUploadSite;
    }

    public String getOrderUnloadSite() {
        return orderUnloadSite;
    }

    public void setOrderUnloadSite(String orderUnloadSite) {
        this.orderUnloadSite = orderUnloadSite;
    }

    public String getLclFreight() {
        return lclFreight;
    }

    public void setLclFreight(String lclFreight) {
        this.lclFreight = lclFreight;
    }

    public BigDecimal getOrderUploadSiteCost() {
        return orderUploadSiteCost;
    }

    public void setOrderUploadSiteCost(BigDecimal orderUploadSiteCost) {
        this.orderUploadSiteCost = orderUploadSiteCost;
    }

    public BigDecimal getOrderUnloadSiteBacost() {
        return orderUnloadSiteBacost;
    }

    public void setOrderUnloadSiteBacost(BigDecimal orderUnloadSiteBacost) {
        this.orderUnloadSiteBacost = orderUnloadSiteBacost;
    }

    public String getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(String minVolume) {
        this.minVolume = minVolume;
    }

    public String getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(String maxVolume) {
        this.maxVolume = maxVolume;
    }

    public BigDecimal getMinVolumeCost() {
        return minVolumeCost;
    }

    public void setMinVolumeCost(BigDecimal minVolumeCost) {
        this.minVolumeCost = minVolumeCost;
    }

    public BigDecimal getMiddleVolumeCost() {
        return middleVolumeCost;
    }

    public void setMiddleVolumeCost(BigDecimal middleVolumeCost) {
        this.middleVolumeCost = middleVolumeCost;
    }

    public BigDecimal getMaxVolumeCost() {
        return maxVolumeCost;
    }

    public void setMaxVolumeCost(BigDecimal maxVolumeCost) {
        this.maxVolumeCost = maxVolumeCost;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    @Override
    public String toString() {
        return "ShRailDivision{" +
        "id=" + id +
        ", lineType=" + lineType +
        ", orderUploadSite=" + orderUploadSite +
        ", orderUnloadSite=" + orderUnloadSite +
        ", lclFreight=" + lclFreight +
        ", orderUploadSiteCost=" + orderUploadSiteCost +
        ", orderUnloadSiteBacost=" + orderUnloadSiteBacost +
        ", minVolume=" + minVolume +
        ", maxVolume=" + maxVolume +
        ", minVolumeCost=" + minVolumeCost +
        ", middleVolumeCost=" + middleVolumeCost +
        ", maxVolumeCost=" + maxVolumeCost +
        ", unit=" + unit +
        ", timeLimit=" + timeLimit +
        ", validStartDate=" + validStartDate +
        ", validEndDate=" + validEndDate +
        "}";
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }
}
