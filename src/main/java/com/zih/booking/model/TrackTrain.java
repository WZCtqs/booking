package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪_班列站到站
 * </p>
 *
 * @author wsl123
 * @since 2020-01-14
 */
@TableName("track_train")
public class TrackTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 班列id
     */
    @TableField("class_id")
    private String classId;
    /**
     * 运踪内容
     */
    @TableField("track_contents")
    private String trackContents;
    /**
     * 实际班列日期
     */
    @TableField("actual_class_date")//箱号
    private String actualClassDate;
    /**
     * 当前位置
     */
    @TableField("current_location")
    private String currentLocation;
    /**
     * 运踪录入时间
     */
    @TableField("track_time")
    private String trackTime;
    /**
     * 预计最早到港时间
     */
    @TableField("except_early_time")
    private String exceptEarlyTime;
    /**
     * 预计最晚到港时间
     */
    @TableField("except_last_time")
    private String exceptLastTime;
    /**
     * 到达/在/驶离
     */
    @TableField("state_value")
    private String stateValue;
    /**
     * 区域
     */
    private String district;
    /**
     * 站点一名字
     */
    @TableField("station_one_name")
    private String stationOneName;
    /**
     * 当前位置距站点一距离
     */
    @TableField("station_one_distance")
    private String stationOneDistance;
    /**
     * 站点二名字
     */
    @TableField("station_two_name")
    private String stationTwoName;
    /**
     * 当前位置距站点二距离
     */
    @TableField("station_two_distance")
    private String stationTwoDistance;
    /**
     * 站点三名字
     */
    @TableField("station_thr_name")
    private String stationThrName;
    /**
     * 当前位置距站点三距离
     */
    @TableField("station_thr_distance")
    private String stationThrDistance;
    /**
     * 站点四名字
     */
    @TableField("station_fou_name")
    private String stationFouName;
    /**
     * 当前位置距站点四距离
     */
    @TableField("station_fou_distance")
    private String stationFouDistance;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTrackContents() {
        return trackContents;
    }

    public void setTrackContents(String trackContents) {
        this.trackContents = trackContents;
    }

    public String getActualClassDate() {
        return actualClassDate;
    }

    public void setActualClassDate(String actualClassDate) {
        this.actualClassDate = actualClassDate;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime;
    }

    public String getExceptEarlyTime() {
        return exceptEarlyTime;
    }

    public void setExceptEarlyTime(String exceptEarlyTime) {
        this.exceptEarlyTime = exceptEarlyTime;
    }

    public String getExceptLastTime() {
        return exceptLastTime;
    }

    public void setExceptLastTime(String exceptLastTime) {
        this.exceptLastTime = exceptLastTime;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStationOneName() {
        return stationOneName;
    }

    public void setStationOneName(String stationOneName) {
        this.stationOneName = stationOneName;
    }

    public String getStationOneDistance() {
        return stationOneDistance;
    }

    public void setStationOneDistance(String stationOneDistance) {
        this.stationOneDistance = stationOneDistance;
    }

    public String getStationTwoName() {
        return stationTwoName;
    }

    public void setStationTwoName(String stationTwoName) {
        this.stationTwoName = stationTwoName;
    }

    public String getStationTwoDistance() {
        return stationTwoDistance;
    }

    public void setStationTwoDistance(String stationTwoDistance) {
        this.stationTwoDistance = stationTwoDistance;
    }

    public String getStationThrName() {
        return stationThrName;
    }

    public void setStationThrName(String stationThrName) {
        this.stationThrName = stationThrName;
    }

    public String getStationThrDistance() {
        return stationThrDistance;
    }

    public void setStationThrDistance(String stationThrDistance) {
        this.stationThrDistance = stationThrDistance;
    }

    public String getStationFouName() {
        return stationFouName;
    }

    public void setStationFouName(String stationFouName) {
        this.stationFouName = stationFouName;
    }

    public String getStationFouDistance() {
        return stationFouDistance;
    }

    public void setStationFouDistance(String stationFouDistance) {
        this.stationFouDistance = stationFouDistance;
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
        return "TrackTrain{" +
        "id=" + id +
        ", classId=" + classId +
        ", trackContents=" + trackContents +
        ", actualClassDate=" + actualClassDate +
        ", currentLocation=" + currentLocation +
        ", trackTime=" + trackTime +
        ", exceptEarlyTime=" + exceptEarlyTime +
        ", exceptLastTime=" + exceptLastTime +
        ", stateValue=" + stateValue +
        ", district=" + district +
        ", stationOneName=" + stationOneName +
        ", stationOneDistance=" + stationOneDistance +
        ", stationTwoName=" + stationTwoName +
        ", stationTwoDistance=" + stationTwoDistance +
        ", stationThrName=" + stationThrName +
        ", stationThrDistance=" + stationThrDistance +
        ", stationFouName=" + stationFouName +
        ", stationFouDistance=" + stationFouDistance +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
