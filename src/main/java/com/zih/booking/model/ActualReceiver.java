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
 * 实际收货方
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@TableName("actual_receiver")
public class ActualReceiver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "act_receiver_id",type= IdType.UUID)
    private String actReceiverId;
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
     * 实际邮箱
     */
    @TableField("actual_email")
    private String actualEmail;
    /**
     * 去回程 0去程 1回城
     */
    @TableField("go_back")
    private String goBack;
    /**
     * 实际提货地址
     */
    @TableField("actual_address")
    private String actualAddress;
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


    public String getActReceiverId() {
        return actReceiverId;
    }

    public void setActReceiverId(String actReceiverId) {
        this.actReceiverId = actReceiverId;
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

    public String getActualEmail() {
        return actualEmail;
    }

    public void setActualEmail(String actualEmail) {
        this.actualEmail = actualEmail;
    }

    public String getGoBack() {
        return goBack;
    }

    public void setGoBack(String goBack) {
        this.goBack = goBack;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
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

    public String getTransitEmail() {
        return transitEmail;
    }

    public void setTransitEmail(String transitEmail) {
        this.transitEmail = transitEmail;
    }

    public String getArriveEmail() {
        return arriveEmail;
    }

    public void setArriveEmail(String arriveEmail) {
        this.arriveEmail = arriveEmail;
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
        return "ActualReceiver{" +
        "actReceiverId=" + actReceiverId +
        ", clientId=" + clientId +
        ", actualMan=" + actualMan +
        ", actualPhone=" + actualPhone +
        ", actualEmail=" + actualEmail +
        ", goBack=" + goBack +
        ", actualAddress=" + actualAddress +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", actualDetailAdd=" + actualDetailAdd +
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
