package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2020-06-06
 */
@TableName("email_record")
public class EmailRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type= IdType.UUID)
    private String id;
    /**
     * 委托书编号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 发送邮箱
     */
    @TableField("send_email")
    private String sendEmail;
    /**
     * 发送人
     */
    @TableField("send_name")
    private String sendName;
    /**
     * 发送时间
     */
    @TableField("send_time")
    private Date sendTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "EmailRecord{" +
        "id=" + id +
        ", orderNumber=" + orderNumber +
        ", sendEmail=" + sendEmail +
        ", sendName=" + sendName +
        ", sendTime=" + sendTime +
        "}";
    }
}
