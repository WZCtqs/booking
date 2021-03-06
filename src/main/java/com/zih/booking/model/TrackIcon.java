package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪图标表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-21
 */
@TableName("track_icon")
public class TrackIcon implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 类型0去程1回程
     */
    @TableField("go_come")
    private Integer goCome;
    /**
     * 整拼箱 0整柜 1拼箱
     */
    @TableField("consolidation_type")
    private Integer consolidationType;
    /**
     * 图标
     */
    private String icon;
    /**
     * 中文名字
     */
    @TableField("name_zh")
    private String nameZh;
    /**
     * 英文名字
     */
    @TableField("name_en")
    private String nameEn;
    /**
     * 排序
     */
    private Integer sort;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoCome() {
        return goCome;
    }

    public void setGoCome(Integer goCome) {
        this.goCome = goCome;
    }

    public Integer getConsolidationType() {
        return consolidationType;
    }

    public void setConsolidationType(Integer consolidationType) {
        this.consolidationType = consolidationType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "TrackIcon{" +
        "id=" + id +
        ", goCome=" + goCome +
        ", consolidationType=" + consolidationType +
        ", icon=" + icon +
        ", nameZh=" + nameZh +
        ", nameEn=" + nameEn +
        ", sort=" + sort +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
