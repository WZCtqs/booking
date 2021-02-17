package com.zih.booking.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 舱位表
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@TableName("busi_shippingspace")
public class BusiShippingspace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排舱主表
     */
    @TableId("space_id")
    private String spaceId;
    /**
     * 舱位号
     */
    @TableField("space_number")
    private Integer spaceNumber;
    /**
     * 舱位类型
     */
    @TableField("space_type_id")
    private String spaceTypeId;
    /**
     * 舱位所属于类型（0整箱，1拼箱）
     */
    @TableField("space_dtype")
    private String spaceDtype;
    /**
     * 剩余的体积
     */
    @TableField("space_rest_size")
    private BigDecimal spaceRestSize;
    /**
     * 剩余的重量
     */
    @TableField("space_rest_weight")
    private BigDecimal spaceRestWeight;
    /**
     * 舱位状态(0代表已满，1代表未满)
     */
    @TableField("space_state")
    private Integer spaceState;
    /**
     * 操作人
     */
    @TableField("space_name")
    private String spaceName;
    /**
     * 操作时间
     */
    @TableField("space_time")
    private Date spaceTime;
    /**
     * 班次编号
     */
    @TableField("class_id")
    private String classId;
    /**
     * 数据状态0为有效1为无效 2为审核数据
     */
    @TableField("source_state")
    private Integer sourceState;
    private String unloadCode;
    private String unloadSite;
    private Date createdate;
    private String uploadCode;
    private String uploadSite;


    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(Integer spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String getSpaceTypeId() {
        return spaceTypeId;
    }

    public void setSpaceTypeId(String spaceTypeId) {
        this.spaceTypeId = spaceTypeId;
    }

    public String getSpaceDtype() {
        return spaceDtype;
    }

    public void setSpaceDtype(String spaceDtype) {
        this.spaceDtype = spaceDtype;
    }

    public BigDecimal getSpaceRestSize() {
        return spaceRestSize;
    }

    public void setSpaceRestSize(BigDecimal spaceRestSize) {
        this.spaceRestSize = spaceRestSize;
    }

    public BigDecimal getSpaceRestWeight() {
        return spaceRestWeight;
    }

    public void setSpaceRestWeight(BigDecimal spaceRestWeight) {
        this.spaceRestWeight = spaceRestWeight;
    }

    public Integer getSpaceState() {
        return spaceState;
    }

    public void setSpaceState(Integer spaceState) {
        this.spaceState = spaceState;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public Date getSpaceTime() {
        return spaceTime;
    }

    public void setSpaceTime(Date spaceTime) {
        this.spaceTime = spaceTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getSourceState() {
        return sourceState;
    }

    public void setSourceState(Integer sourceState) {
        this.sourceState = sourceState;
    }

    public String getUnloadCode() {
        return unloadCode;
    }

    public void setUnloadCode(String unloadCode) {
        this.unloadCode = unloadCode;
    }

    public String getUnloadSite() {
        return unloadSite;
    }

    public void setUnloadSite(String unloadSite) {
        this.unloadSite = unloadSite;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUploadCode() {
        return uploadCode;
    }

    public void setUploadCode(String uploadCode) {
        this.uploadCode = uploadCode;
    }

    public String getUploadSite() {
        return uploadSite;
    }

    public void setUploadSite(String uploadSite) {
        this.uploadSite = uploadSite;
    }

    @Override
    public String toString() {
        return "BusiShippingspace{" +
        "spaceId=" + spaceId +
        ", spaceNumber=" + spaceNumber +
        ", spaceTypeId=" + spaceTypeId +
        ", spaceDtype=" + spaceDtype +
        ", spaceRestSize=" + spaceRestSize +
        ", spaceRestWeight=" + spaceRestWeight +
        ", spaceState=" + spaceState +
        ", spaceName=" + spaceName +
        ", spaceTime=" + spaceTime +
        ", classId=" + classId +
        ", sourceState=" + sourceState +
        ", unloadCode=" + unloadCode +
        ", unloadSite=" + unloadSite +
        ", createdate=" + createdate +
        ", uploadCode=" + uploadCode +
        ", uploadSite=" + uploadSite +
        "}";
    }
}
