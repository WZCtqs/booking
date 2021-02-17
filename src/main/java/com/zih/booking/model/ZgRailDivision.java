package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 整柜铁路运费表
 * </p>
 *
 * @author shahy123
 * @since 2020-03-17
 */
@TableName("zg_rail_division")
public class ZgRailDivision implements Serializable {

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
     * 箱型值
     */
    @TableField("container_type_value")
    private String containerTypeValue;
    /**
     * （0是货主自备箱SOC，1是承运人自备箱COC）
     */
    @TableField("is_container")
    private String isContainer;
    /**
     * 铁路运费
     */
    @TableField("rail_cost")
    private String railCost;
    /**
     * （0，整箱；1拼箱）
     */
    @TableField("is_consolidation")
    private String isConsolidation;
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

    public String getContainerTypeValue() {
        return containerTypeValue;
    }

    public void setContainerTypeValue(String containerTypeValue) {
        this.containerTypeValue = containerTypeValue;
    }

    public String getIsContainer() {
        return isContainer;
    }

    public void setIsContainer(String isContainer) {
        this.isContainer = isContainer;
    }

    public String getRailCost() {
        return railCost;
    }

    public void setRailCost(String railCost) {
        this.railCost = railCost;
    }

    public String getIsConsolidation() {
        return isConsolidation;
    }

    public void setIsConsolidation(String isConsolidation) {
        this.isConsolidation = isConsolidation;
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
        return "ZgRailDivision{" +
        "id=" + id +
        ", lineType=" + lineType +
        ", orderUploadSite=" + orderUploadSite +
        ", orderUnloadSite=" + orderUnloadSite +
        ", containerTypeValue=" + containerTypeValue +
        ", isContainer=" + isContainer +
        ", railCost=" + railCost +
        ", isConsolidation=" + isConsolidation +
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
