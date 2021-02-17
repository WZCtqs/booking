package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-20
 */
@TableName("sys_dept")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;
    /**
     * 父部门id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;
    /**
     * 显示顺序
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
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
     * 部门编号
     */
    @TableField("dept_code")
    private String deptCode;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮政区号
     */
    @TableField("organization_zipcode")
    private String organizationZipcode;
    /**
     * 副负责人
     */
    @TableField("organization_assistant_manager")
    private String organizationAssistantManager;
    /**
     * 内线电话
     */
    @TableField("organization_outer_phone")
    private String organizationOuterPhone;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationZipcode() {
        return organizationZipcode;
    }

    public void setOrganizationZipcode(String organizationZipcode) {
        this.organizationZipcode = organizationZipcode;
    }

    public String getOrganizationAssistantManager() {
        return organizationAssistantManager;
    }

    public void setOrganizationAssistantManager(String organizationAssistantManager) {
        this.organizationAssistantManager = organizationAssistantManager;
    }

    public String getOrganizationOuterPhone() {
        return organizationOuterPhone;
    }

    public void setOrganizationOuterPhone(String organizationOuterPhone) {
        this.organizationOuterPhone = organizationOuterPhone;
    }

    @Override
    public String toString() {
        return "SysDept{" +
        "deptId=" + deptId +
        ", parentId=" + parentId +
        ", ancestors=" + ancestors +
        ", deptName=" + deptName +
        ", orderNum=" + orderNum +
        ", leader=" + leader +
        ", phone=" + phone +
        ", email=" + email +
        ", status=" + status +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", deptCode=" + deptCode +
        ", address=" + address +
        ", organizationZipcode=" + organizationZipcode +
        ", organizationAssistantManager=" + organizationAssistantManager +
        ", organizationOuterPhone=" + organizationOuterPhone +
        "}";
    }
}
