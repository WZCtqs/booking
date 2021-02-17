package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪二级节点表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-25
 */
@TableName("track_two_level")
public class TrackTwoLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 一级节点id
     */
    @TableField("one_id")
    private Long oneId;
    /**
     * 是否客户端二级节点0是1否
     */
    @TableField("is_custom")
    private String isCustom;
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
     * 1正常  2异常
     */
    private Integer state;
    /**
     * 时间
     */
    private String time;
    /**
     * 顺序
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
    /**
     * 备注英文
     */
    @TableField("remark_en")
    private String remarkEn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setRemarkEn(String remarkEn) {
        this.remarkEn = remarkEn;
    }

    public String getRemarkEn() {
        return remarkEn;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOneId() {
        return oneId;
    }

    public void setOneId(Long oneId) {
        this.oneId = oneId;
    }

    public String getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(String isCustom) {
        this.isCustom = isCustom;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "TrackTwoLevel{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", oneId=" + oneId +
                ", isCustom=" + isCustom +
                ", nameZh=" + nameZh +
                ", nameEn=" + nameEn +
                ", state=" + state +
                ", time=" + time +
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
