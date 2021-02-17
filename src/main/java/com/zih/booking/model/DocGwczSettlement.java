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
 * @since 2020-12-09
 */
@TableName("doc_gwcz_settlement")
public class DocGwczSettlement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 托书id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 托书编号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 箱号
     */
    @TableField("container_no")
    private String containerNo;
    /**
     * 拼箱体积
     */
    @TableField("px_volume")
    private Double pxVolume;
    /**
     * 拼箱计费体积
     */
    @TableField("px_settlement_volume")
    private Double pxSettlementVolume;
    /**
     * 拼箱重量
     */
    @TableField("px_weight")
    private Double pxWeight;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public Double getPxVolume() {
        return pxVolume;
    }

    public void setPxVolume(Double pxVolume) {
        this.pxVolume = pxVolume;
    }

    public Double getPxSettlementVolume() {
        return pxSettlementVolume;
    }

    public void setPxSettlementVolume(Double pxSettlementVolume) {
        this.pxSettlementVolume = pxSettlementVolume;
    }

    public Double getPxWeight() {
        return pxWeight;
    }

    public void setPxWeight(Double pxWeight) {
        this.pxWeight = pxWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DocGwczSettlement{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", orderNumber=" + orderNumber +
        ", containerNo=" + containerNo +
        ", pxVolume=" + pxVolume +
        ", pxSettlementVolume=" + pxSettlementVolume +
        ", pxWeight=" + pxWeight +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
