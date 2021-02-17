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
 * 实际发货方
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@TableName("actual_sender")
public class ActualSender implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "act_sender_id",type= IdType.UUID)
    private String actSenderId;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 实际联系人
     */
    @TableField("actual_man")
    private String actualMan;
    /**
     * 实际联系方式
     */
    @TableField("actual_phone")
    private String actualPhone;
    /**
     * 实际提货地址
     */
    @TableField("actual_address")
    private String actualAddress;
    /**
     * 去回程 0去程 1回城
     */
    @TableField("go_back")
    private String goBack;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 实际详细地址
     */
    @TableField("actual_detail_add")
    private String actualDetailAdd;
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
    /**
     * 实际邮箱
     */
    @TableField("actual_email")
    private String actualEmail;

    public String getActualEmail() {
        return actualEmail;
    }

    public void setActualEmail(String actualEmail) {
        this.actualEmail = actualEmail;
    }

    public String getActSenderId() {
        return actSenderId;
    }

    public void setActSenderId(String actSenderId) {
        this.actSenderId = actSenderId;
    }



    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getActualMan() {
        return actualMan;
    }

    public void setActualMan(String actualMan) {
        this.actualMan = actualMan;
    }

    public String getActualPhone() {
        return actualPhone;
    }

    public void setActualPhone(String actualPhone) {
        this.actualPhone = actualPhone;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getGoBack() {
        return goBack;
    }

    public void setGoBack(String goBack) {
        this.goBack = goBack;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getActualDetailAdd() {
        return actualDetailAdd;
    }

    public void setActualDetailAdd(String actualDetailAdd) {
        this.actualDetailAdd = actualDetailAdd;
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
        return "ActualSender{" +
        "actSenderId=" + actSenderId +
        ", clientId=" + clientId +
        ", actualMan=" + actualMan +
        ", actualPhone=" + actualPhone +
        ", actualAddress=" + actualAddress +
        ", goBack=" + goBack +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", actualDetailAdd=" + actualDetailAdd +
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
