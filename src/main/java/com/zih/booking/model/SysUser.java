package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-20
 */
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField("dc_user_id")
    private String dcUserId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 用户账号
     */
    @TableField("user_name")
    private String userName;
    /**
     * 姓名
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
    private String userType;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 最后登陆时间
     */
    @TableField("login_date")
    private Date loginDate;
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
    /**
     * 备注
     */
    private String remark;
    /**
     * 开票申请人
     */
    private String kpapplicant;
    /**
     * 开票业务部门
     */
    private String kpOrganization;
    /**
     * 开票跟单员
     */
    private String kpMerchandiser;
    /**
     * 开票申请部门
     */
    private String kpapplydepartment;
    /**
     * 开票申请人部门经理
     */
    private String kpdepartmanager;
    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;
    /**
     * 推荐人状态(0,否，1是)
     */
    @TableField("reference_type")
    private String referenceType;
    /**
     * 推荐人id
     */
    @TableField("tjr_id")
    private String tjrId;
    /**
     * 推荐人小组内其他邮箱地址
     */
    @TableField("reference_other_mails")
    private String referenceOtherMails;

    public String getDcUserId() {
        return dcUserId;
    }

    public void setDcUserId(String dcUserId) {
        this.dcUserId = dcUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKpapplicant() {
        return kpapplicant;
    }

    public void setKpapplicant(String kpapplicant) {
        this.kpapplicant = kpapplicant;
    }

    public String getKpOrganization() {
        return kpOrganization;
    }

    public void setKpOrganization(String kpOrganization) {
        this.kpOrganization = kpOrganization;
    }

    public String getKpMerchandiser() {
        return kpMerchandiser;
    }

    public void setKpMerchandiser(String kpMerchandiser) {
        this.kpMerchandiser = kpMerchandiser;
    }

    public String getKpapplydepartment() {
        return kpapplydepartment;
    }

    public void setKpapplydepartment(String kpapplydepartment) {
        this.kpapplydepartment = kpapplydepartment;
    }

    public String getKpdepartmanager() {
        return kpdepartmanager;
    }

    public void setKpdepartmanager(String kpdepartmanager) {
        this.kpdepartmanager = kpdepartmanager;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getTjrId() {
        return tjrId;
    }

    public void setTjrId(String tjrId) {
        this.tjrId = tjrId;
    }

    public String getReferenceOtherMails() {
        return referenceOtherMails;
    }

    public void setReferenceOtherMails(String referenceOtherMails) {
        this.referenceOtherMails = referenceOtherMails;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "userId=" + userId +
        ", deptId=" + deptId +
        ", userName=" + userName +
        ", nickName=" + nickName +
        ", userType=" + userType +
        ", email=" + email +
        ", phonenumber=" + phonenumber +
        ", sex=" + sex +
        ", avatar=" + avatar +
        ", password=" + password +
        ", status=" + status +
        ", delFlag=" + delFlag +
        ", loginIp=" + loginIp +
        ", loginDate=" + loginDate +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        ", kpapplicant=" + kpapplicant +
        ", kpOrganization=" + kpOrganization +
        ", kpMerchandiser=" + kpMerchandiser +
        ", kpapplydepartment=" + kpapplydepartment +
        ", kpdepartmanager=" + kpdepartmanager +
        ", jobNumber=" + jobNumber +
        ", referenceType=" + referenceType +
        ", tjrId=" + tjrId +
        ", referenceOtherMails=" + referenceOtherMails +
        "}";
    }
}
