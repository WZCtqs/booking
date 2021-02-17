package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 提还箱地和费用规则
 * </p>
 *
 * @author wsl123
 * @since 2020-04-03
 */
@TableName("busi_boxfee")
public class BusiBoxfee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("Box_id")
    private String boxId;
    /**
     * 集装箱类型：0普通箱 1特种箱
     */
    @TableField("Box_type")
    private String boxType;
    /**
     * 选择类型：0,提箱；1,还箱
     */
    @TableField("address_type")
    private String addressType;
    /**
     * 提/还箱地点中文
     */
    private String address;
    /**
     * 提/还箱地点英文
     */
    @TableField("address_en")
    private String addressEn;
    /**
     * 箱型
     */
    @TableField("container_type")
    private String containerType;
    /**
     * 状态 0禁用 1启用
     */
    private String state;
    /**
     * 平衡费用
     */
    private String cost;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 截止时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 创建人id
     */
    private String createuserid;
    /**
     * 创建人用户名
     */
    private String createusername;
    /**
     * 创建时间
     */
    private Date createdate;
    @TableField("line_type")
    private String lineType;

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "BusiBoxfee{" +
        "boxId=" + boxId +
        ", boxType=" + boxType +
        ", addressType=" + addressType +
        ", address=" + address +
        ", addressEn=" + addressEn +
        ", containerType=" + containerType +
        ", state=" + state +
        ", cost=" + cost +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", createuserid=" + createuserid +
        ", createusername=" + createusername +
        ", createdate=" + createdate +
        "}";
    }
}
