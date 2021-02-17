package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪_异常箱
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@TableName("track_abnormal_box")
public class TrackAbnormalBox implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单id（舱位号）
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 是否脱离主班列0是1否
     */
    @TableField("is_separate")
    private Integer isSeparate;
    /**
     * 异常类型
     */
    @TableField("abnormal_type")
    private String abnormalType;
    /**
     * 异常信息
     */
    @TableField("abnormal_information")
    private String abnormalInformation;
    /**
     * 箱号
     */
    @TableField("box_num")
    private String boxNum;
    /**
     * 下班列原因
     */
    @TableField("unload_reason")
    private String unloadReason;
    /**
     * 下班列地点
     */
    @TableField("unload_site")
    private String unloadSite;
    /**
     * 收件邮箱
     */
    @TableField("receive_emails")
    private String receiveEmails;
    /**
     * 密送邮箱
     */
    @TableField("secret_emails")
    private String secretEmails;
    /**
     * 下货时间
     */
    @TableField("unload_time")
    private String unloadTime;
    /**
     * 解决时间
     */
    @TableField("solve_time")
    private String solveTime;
    /**
     * 录入时间
     */
    @TableField("input_time")
    private String inputTime;
    /**
     * 对接负责人
     */
    @TableField("charge_person")
    private String chargePerson;
    /**
     * 真实原因
     */
    @TableField("real_reason")
    private String realReason;
    /**
     * 到站时间
     */
    @TableField("arrive_time")
    private String arriveTime;
    /**
     * 查验类型0正常查验1异常查验
     */
    @TableField("inspection_type")
    private Integer inspectionType;
    /**
     * 加挂班列编号
     */
    @TableField("class_num")
    private String classNum;
    /**
     * 是否到站0到站1未到站
     */
    @TableField("is_arrive")
    private Integer isArrive;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志0正常1删除
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


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

    public Integer getIsSeparate() {
        return isSeparate;
    }

    public void setIsSeparate(Integer isSeparate) {
        this.isSeparate = isSeparate;
    }

    public String getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalInformation() {
        return abnormalInformation;
    }

    public void setAbnormalInformation(String abnormalInformation) {
        this.abnormalInformation = abnormalInformation;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public String getUnloadReason() {
        return unloadReason;
    }

    public void setUnloadReason(String unloadReason) {
        this.unloadReason = unloadReason;
    }

    public String getUnloadSite() {
        return unloadSite;
    }

    public void setUnloadSite(String unloadSite) {
        this.unloadSite = unloadSite;
    }

    public String getReceiveEmails() {
        return receiveEmails;
    }

    public void setReceiveEmails(String receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    public String getSecretEmails() {
        return secretEmails;
    }

    public void setSecretEmails(String secretEmails) {
        this.secretEmails = secretEmails;
    }

    public String getUnloadTime() {
        return unloadTime;
    }

    public void setUnloadTime(String unloadTime) {
        this.unloadTime = unloadTime;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getRealReason() {
        return realReason;
    }

    public void setRealReason(String realReason) {
        this.realReason = realReason;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(Integer inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Integer getIsArrive() {
        return isArrive;
    }

    public void setIsArrive(Integer isArrive) {
        this.isArrive = isArrive;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TrackAbnormalBox{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", isSeparate=" + isSeparate +
        ", abnormalType=" + abnormalType +
        ", abnormalInformation=" + abnormalInformation +
        ", boxNum=" + boxNum +
        ", unloadReason=" + unloadReason +
        ", unloadSite=" + unloadSite +
        ", receiveEmails=" + receiveEmails +
        ", secretEmails=" + secretEmails +
        ", unloadTime=" + unloadTime +
        ", solveTime=" + solveTime +
        ", inputTime=" + inputTime +
        ", chargePerson=" + chargePerson +
        ", realReason=" + realReason +
        ", arriveTime=" + arriveTime +
        ", inspectionType=" + inspectionType +
        ", classNum=" + classNum +
        ", isArrive=" + isArrive +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
