package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪_中亚境外
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@TableName("track_abroad")
public class TrackAbroad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

     /**
     * 订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 箱号
     */
    @TableField("box_num")
    private String boxNum;

    /**
     * 跟单接收邮箱
     */
    @TableField("documentary_emails")
    private String documentaryEmails;
    /**
     * 业务接收邮箱business_emails
     */
    @TableField("business_emails")
    private String businessEmails;
    /**
     * 发车日期departure_date
     */
    @TableField("departure_date")
    private String departureDate;
    /**
     * 驶离时间leave_time
     */
    @TableField("leave_time")
    private String leaveTime;
    /**
     * 换装时间change_time
     */
    @TableField("change_time")
    private String changeTime;
    /**
     * 换装车板号change_num
     */
    @TableField("change_num")
    private String changeNum;
    /**
     * 运踪信息abroad_contents
     */
    @TableField("abroad_contents")
    private String abroadContents;
    /**
     * 运踪时间
     */
    @TableField("track_time")
    private String trackTime;
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

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public String getDocumentaryEmails() {
        return documentaryEmails;
    }

    public void setDocumentaryEmails(String documentaryEmails) {
        this.documentaryEmails = documentaryEmails;
    }

    public String getBusinessEmails() {
        return businessEmails;
    }

    public void setBusinessEmails(String businessEmails) {
        this.businessEmails = businessEmails;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(String changeNum) {
        this.changeNum = changeNum;
    }

    public String getAbroadContents() {
        return abroadContents;
    }

    public void setAbroadContents(String abroadContents) {
        this.abroadContents = abroadContents;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime;
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
        return "TrackAbroad{" +
        "id=" + id +
        ", documentaryEmails=" + documentaryEmails +
        ", orderId=" + orderId +
        ", boxNum=" + boxNum +
        ", businessEmails=" + businessEmails +
        ", departureDate=" + departureDate +
        ", leaveTime=" + leaveTime +
        ", changeTime=" + changeTime +
        ", changeNum=" + changeNum +
        ", abroadContents=" + abroadContents +
        ", trackTime=" + trackTime +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
