package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户端用户表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-15
 */
@TableName("kh_user")
public class KhUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 类型
     */
    private String type;
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
    private Date updateBy;
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
     * 删除标志（0正常，2删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 菜单权限id
     */
    @TableField("mp_id")
    private Integer mpId;
    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    @TableField("order_email")
    private String orderEmail;

    @TableField("order_contacts")
    private String orderContacts;

    @TableField("order_contact_info")
    private String orderContactInfo;

    @TableField("order_contact_address")
    private String orderContactAddress;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }
    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderContacts(String orderContacts) {
        this.orderContacts = orderContacts;
    }
    public String getOrderContacts() {
        return orderContacts;
    }

    public void setOrderContactInfo(String orderContactInfo) {
        this.orderContactInfo = orderContactInfo;
    }
    public String getOrderContactInfo() {
        return orderContactInfo;
    }

    public void setOrderContactAddress(String orderContactAddress) {
        this.orderContactAddress = orderContactAddress;
    }
    public String getOrderContactAddress() {
        return orderContactAddress;
    }

    @Override
    public String toString() {
        return "KhUser{" +
        "id=" + id +
        ", clientId=" + clientId +
        ", userName=" + userName +
        ", password=" + password +
        ", phone=" + phone +
        ", type=" + type +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", mpId=" + mpId +
        ", jobNumber=" + jobNumber +
        ", orderEmail=" + orderEmail +
        ", orderContacts=" + orderContacts +
        ", orderContactInfo=" + orderContactInfo +
        ", orderContactAddress=" + orderContactAddress +
        "}";
    }
}
