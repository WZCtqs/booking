package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪_货物状态表--以舱位号为单位，标记是否发车
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@TableName("track_goods_status")
public class TrackGoodsStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 实际班列id
     */
    @TableField("actual_class_id")
    private String actualClassId;
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
     * 货物是否上车0是1否
     */
    @TableField("is_getin")
    private Integer isGetin;
    /**
     * 实际班列日期
     */
    @TableField("actual_class_date")
    private String actualClassDate;
    /**
     * 是否正常箱0是1否
     */
    @TableField("is_normal")
    private Integer isNormal;
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
    //封号
    @TableField("seal_num")
    private String sealNum;
    //来源系统
    @TableField("from_system")
    private String fromSystem;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField("abnormal_time")
    private Date abnormalTime;
    @TableField("batch_time")
    private Date batchTime;
    //batch_time 分批实际班列日期1
    @TableField("batch_date")
    private String batchDate;
    //batch_time 分批时间2
    @TableField("batch_time2")
    private Date batchTime2;
    //batch_time 分批实际班列日期2
    @TableField("batch_date2")
    private String batchDate2;
    //batch_time 分批时间3
    @TableField("batch_time3")
    private Date batchTime3;
    //batch_time 分批实际班列日期3
    @TableField("batch_date3")
    private String batchDate3;
    //batch_time 分批时间4
    @TableField("batch_time4")
    private Date batchTime4;
    //batch_time 分批实际班列日期4
    @TableField("batch_date4")
    private String batchDate4;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActualClassId() {
        return actualClassId;
    }

    public void setActualClassId(String actualClassId) {
        this.actualClassId = actualClassId;
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

    public Integer getIsGetin() {
        return isGetin;
    }

    public void setIsGetin(Integer isGetin) {
        this.isGetin = isGetin;
    }

    public String getActualClassDate() {
        return actualClassDate;
    }

    public void setActualClassDate(String actualClassDate) {
        this.actualClassDate = actualClassDate;
    }

    public Integer getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Integer isNormal) {
        this.isNormal = isNormal;
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
    public Date getAbnormalTime() {
        return abnormalTime;
    }

    public void setAbnormalTime(Date abnormalTime) {
        this.abnormalTime = abnormalTime;
    }
    public Date getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(Date batchTime) {
        this.batchTime = batchTime;
    }
    public String getBatchDate() { return batchDate; }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }
    public String getSealNum() {
        return sealNum;
    }

    public void setSealNum(String sealNum) {
        this.sealNum = sealNum;
    }
    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public Date getBatchTime2() {
        return batchTime2;
    }

    public void setBatchTime2(Date batchTime2) {
        this.batchTime2 = batchTime2;
    }

    public String getBatchDate2() {
        return batchDate2;
    }

    public void setBatchDate2(String batchDate2) {
        this.batchDate2 = batchDate2;
    }



    public Date getBatchTime3() {
        return batchTime3;
    }

    public void setBatchTime3(Date batchTime3) {
        this.batchTime3 = batchTime3;
    }

    public String getBatchDate3() {
        return batchDate3;
    }

    public void setBatchDate3(String batchDate3) {
        this.batchDate3 = batchDate3;
    }



    public Date getBatchTime4() {
        return batchTime4;
    }

    public void setBatchTime4(Date batchTime4) {
        this.batchTime4 = batchTime4;
    }

    public String getBatchDate4() {
        return batchDate4;
    }

    public void setBatchDate4(String batchDate4) {
        this.batchDate4 = batchDate4;
    }
    @Override
    public String toString() {
        return "TrackGoodsStatus{" +
        "id=" + id +
        ", actualClassId=" + actualClassId +
        ", orderId=" + orderId +
        ", boxNum=" + boxNum +
        ", isGetin=" + isGetin +
        ", actualClassDate=" + actualClassDate +
        ", isNormal=" + isNormal +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
