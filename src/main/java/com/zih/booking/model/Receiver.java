package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2020-02-25
 */
public class Receiver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "receiver_id",type= IdType.UUID)
    private String receiverId;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 收货方名称
     */
    @TableField("receiver_name")
    private String receiverName;

    @TableField("receiver_man")
    private String receiverMan;
    /**
     * 收货方地址
     */
    @TableField("receiver_address")
    private String receiverAddress;
    /**
     * 收货方电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;
    /**
     * 收货方邮箱
     */
    @TableField("receiver_email")
    private String receiverEmail;
    /**
     * 在途信息接收邮箱
     */
    @TableField("transit_email")
    private String transitEmail;
    /**
     * 到站通知邮箱
     */
    @TableField("arrive_email")
    private String arriveEmail;
    /**
    /**
     * 排序字段
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 删除标识 0未删除 1已删除
     */
    @TableField("del_flag")
    private String delFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private Date updateTime;

    public String getReceiverMan() {
        return receiverMan;
    }

    public void setReceiverMan(String receiverMan) {
        this.receiverMan = receiverMan;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public void setTransitEmail(String transitEmail) {
        this.transitEmail = transitEmail;
    }
    public String getTransitEmail() {
        return transitEmail;
    }

    public void setArriveEmail(String arriveEmail) {
        this.arriveEmail = arriveEmail;
    }
    public String getArriveEmail() {
        return arriveEmail;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
        return "Receiver{" +
        "receiverId=" + receiverId +
        ", clientId=" + clientId +
        ", receiverName=" + receiverName +
        ", receiverAddress=" + receiverAddress +
        ", receiverPhone=" + receiverPhone +
        ", receiverEmail=" + receiverEmail +
        ", transitEmail=" + transitEmail +
        ", arriveEmail=" + arriveEmail +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
