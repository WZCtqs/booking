package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 推荐人表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-03
 */
@TableName("system_dict")
public class SystemDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父id
     */
    private Integer fid;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 名称
     */
    private String mCheng;
    /**
     * 状态
     */
    private String state;
    /**
     * 排序
     */
    private String sort;
    /**
     * 0  中文  1 英文
     */
    private String yuyan;
    /**
     * 西向跟单员
     */
    @TableField("west_merchandiser")
    private String westMerchandiser;
    /**
     * 西向跟单员id
     */
    @TableField("west_merchandiser_id")
    private Integer westMerchandiserId;
    /**
     * 东向跟单员名称
     */
    @TableField("east_merchandiser")
    private String eastMerchandiser;
    /**
     * 东向跟单员id
     */
    @TableField("east_merchandiser_id")
    private Integer eastMerchandiserId;
    /**
     * 邮箱以分号隔开
     */
    private String mails;
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
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getmCheng() {
        return mCheng;
    }

    public void setmCheng(String mCheng) {
        this.mCheng = mCheng;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getYuyan() {
        return yuyan;
    }

    public void setYuyan(String yuyan) {
        this.yuyan = yuyan;
    }

    public String getWestMerchandiser() {
        return westMerchandiser;
    }

    public void setWestMerchandiser(String westMerchandiser) {
        this.westMerchandiser = westMerchandiser;
    }

    public Integer getWestMerchandiserId() {
        return westMerchandiserId;
    }

    public void setWestMerchandiserId(Integer westMerchandiserId) {
        this.westMerchandiserId = westMerchandiserId;
    }

    public String getEastMerchandiser() {
        return eastMerchandiser;
    }

    public void setEastMerchandiser(String eastMerchandiser) {
        this.eastMerchandiser = eastMerchandiser;
    }

    public Integer getEastMerchandiserId() {
        return eastMerchandiserId;
    }

    public void setEastMerchandiserId(Integer eastMerchandiserId) {
        this.eastMerchandiserId = eastMerchandiserId;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SystemDict{" +
        "id=" + id +
        ", fid=" + fid +
        ", ancestors=" + ancestors +
        ", mCheng=" + mCheng +
        ", state=" + state +
        ", sort=" + sort +
        ", yuyan=" + yuyan +
        ", westMerchandiser=" + westMerchandiser +
        ", westMerchandiserId=" + westMerchandiserId +
        ", eastMerchandiser=" + eastMerchandiser +
        ", eastMerchandiserId=" + eastMerchandiserId +
        ", mails=" + mails +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
