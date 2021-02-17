package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 消息提醒
 * </p>
 *
 * @author wsl123
 * @since 2020-04-20
 */
@TableName("sys_message")
public class SysMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 委托书编号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 标题
     */
    @TableField("message_title")
    private String messageTitle;
    /**
     * 类型名称
     */
    @TableField("message_type")
    private String messageType;
    /**
     * 内容
     */
    @TableField("message_content")
    private String messageContent;
    /**
     * 状态 0 未处理 1已处理
     */
    @TableField("msg_status")
    private Integer msgStatus;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 单证消息时存放key
     */
    @TableField("file_type_key")
    private String fileTypeKey;
    /**
     * 短信发送结果（0：成功 1：失败）
     */
    @TableField("sms_status")
    private Integer smsStatus;
    /**
     * 邮件发送结果（0：成功 1：失败）
     */
    @TableField("email_status")
    private Integer emailStatus;
    /**
     * 短信发送失败原因
     */
    private String smsfail;
    /**
     * 邮箱发送失败原因
     */
    private String emailfail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getFileTypeKey() {
        return fileTypeKey;
    }

    public void setFileTypeKey(String fileTypeKey) {
        this.fileTypeKey = fileTypeKey;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getSmsfail() {
        return smsfail;
    }

    public void setSmsfail(String smsfail) {
        this.smsfail = smsfail;
    }

    public String getEmailfail() {
        return emailfail;
    }

    public void setEmailfail(String emailfail) {
        this.emailfail = emailfail;
    }

    @Override
    public String toString() {
        return "SysMessage{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", clientId=" + clientId +
                ", orderNumber=" + orderNumber +
                ", messageTitle=" + messageTitle +
                ", messageType=" + messageType +
                ", messageContent=" + messageContent +
                ", msgStatus=" + msgStatus +
                ", createTime=" + createTime +
                ", delFlag=" + delFlag +
                ", fileTypeKey=" + fileTypeKey +
                ", smsStatus=" + smsStatus +
                ", emailStatus=" + emailStatus +
                ", smsfail=" + smsfail +
                ", emailfail=" + emailfail +
                "}";
    }
}
