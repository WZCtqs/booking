package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 问题反馈
 * </p>
 *
 * @author wsl123
 * @since 2020-02-27
 */
@TableName("problem_feedback")
public class ProblemFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "problem_id",type = IdType.UUID)
    private String problemId;
    @TableField("client_id")
    private String clientId;
    /**
     * 委托书编号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 集装箱号
     */
    @TableField("container_number")
    private String containerNumber;
    /**
     * 状态 0待回复 1已回复
     */
    private String status;
    //是否运费支付0是1否
    @TableField("is_pay")
    private String isPay;
    /**
     * 签收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("sign_time")
    private Date signTime;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 投诉内容
     */
    @TableField("problem_content")
    private String problemContent;
    /**
     * 客户要求
     */
    private String requirement;
    /**
     * 投诉反馈
     */
    private String feedback;
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

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
        return "ProblemFeedback{" +
        "problemId=" + problemId +
        ", orderNumber=" + orderNumber +
        ", containerNumber=" + containerNumber +
        ", status=" + status +
        ", signTime=" + signTime +
        ", email=" + email +
        ", problemContent=" + problemContent +
        ", requirement=" + requirement +
        ", feedback=" + feedback +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
