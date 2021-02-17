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
public class Sender implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value="sender_id",type= IdType.UUID)
    private String senderId;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 发货方名称
     */
    @TableField("sender_name")
    private String senderName;
    @TableField("sender_man")
    private String senderMan;
    /**
     * 发货方地址
     */
    @TableField("sender_address")
    private String senderAddress;
    /**
     * 发货方电话
     */
    @TableField("sender_phone")
    private String senderPhone;
    /**
     * 发货方邮箱
     */
    @TableField("sender_email")
    private String senderEmail;
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

    public String getSenderMan() {
        return senderMan;
    }

    public void setSenderMan(String senderMan) {
        this.senderMan = senderMan;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
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
        return "Sender{" +
        "senderId=" + senderId +
        ", clientId=" + clientId +
        ", senderName=" + senderName +
        ", senderAddress=" + senderAddress +
        ", senderPhone=" + senderPhone +
        ", senderEmail=" + senderEmail +
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
