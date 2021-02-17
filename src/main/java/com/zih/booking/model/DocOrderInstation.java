package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2021-02-03
 */
@TableName("doc_order_instation")
public class DocOrderInstation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * orderid
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 封号
     */
    private String sealnum;
    /**
     * 报关员
     */
    private String customsbroker;
    /**
     * 箱型
     */
    @TableField("container_type")
    private String containerType;
    /**
     * 箱号
     */
    @TableField("container_no")
    private String containerNo;
    /**
     * 进站时间
     */
    @TableField("instation_time")
    private String instationTime;
    @TableField("create_time")
    private Date createTime;
    @TableField("from_system")
    private String fromSystem;

    @TableField(exist = false)
    private String orderNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSealnum() {
        return sealnum;
    }

    public void setSealnum(String sealnum) {
        this.sealnum = sealnum;
    }

    public String getCustomsbroker() {
        return customsbroker;
    }

    public void setCustomsbroker(String customsbroker) {
        this.customsbroker = customsbroker;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getInstationTime() {
        return instationTime;
    }

    public void setInstationTime(String instationTime) {
        this.instationTime = instationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "DocOrderInstation{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", sealnum=" + sealnum +
        ", customsbroker=" + customsbroker +
        ", containerType=" + containerType +
        ", containerNo=" + containerNo +
        ", instationTime=" + instationTime +
        ", createTime=" + createTime +
        ", fromSystem=" + fromSystem +
        "}";
    }
}
