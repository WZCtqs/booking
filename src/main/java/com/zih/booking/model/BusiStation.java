package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 场站地址
 * </p>
 *
 * @author wsl123
 * @since 2020-10-15
 */
@TableName("busi_station")
public class BusiStation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("station_id")
    private String stationId;
    /**
     * 上货站唯一码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 上货站名称
     */
    @TableField("class_site")
    private String classSite;
    /**
     * 车站名称
     */
    private String statioin;
    /**
     * 截港时间
     */
    private String cuntofftime;
    /**
     * 整箱地址
     */
    @TableField("zx_address")
    private String zxAddress;
    /**
     * 车站地址
     */
    @TableField("px_ystype")
    private String pxYstype;
    /**
     * 拼箱地址
     */
    @TableField("px_address")
    private String pxAddress;
    /**
     * 联系方式
     */
    private String contacts;
    /**
     * 1：拼箱0：整箱
     */
    private String IsConsolidation;
    /**
     * 创建日期
     */
    private Date createdate;
    /**
     * 创建者
     */
    private String createuser;
    /**
     * 默认0，启用，1是禁用
     */
    private String IsEnabled;
    /**
     * 是否报关 0否 1是
     */
    @TableField("is_report")
    private String isReport;
    /**
     * 整箱报关
     */
    @TableField("fcl_customs")
    private Integer fclCustoms;
    /**
     * 整箱非报关
     */
    @TableField("fcl_customs_not")
    private Integer fclCustomsNot;
    /**
     * 拼箱报关
     */
    @TableField("lcl_customs")
    private Integer lclCustoms;
    /**
     * 拼箱非报关
     */
    @TableField("lcl_customs_not")
    private Integer lclCustomsNot;
    /**
     * 截港时间
     */
    @TableField("fcl_customs_time")
    private String fclCustomsTime;
    /**
     * 截港时间
     */
    @TableField("fcl_customs_not_time")
    private String fclCustomsNotTime;
    /**
     * 截港时间
     */
    @TableField("lcl_customs_time")
    private String lclCustomsTime;
    /**
     * 截港时间
     */
    @TableField("lcl_customs_not_time")
    private String lclCustomsNotTime;


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassSite() {
        return classSite;
    }

    public void setClassSite(String classSite) {
        this.classSite = classSite;
    }

    public String getStatioin() {
        return statioin;
    }

    public void setStatioin(String statioin) {
        this.statioin = statioin;
    }

    public String getCuntofftime() {
        return cuntofftime;
    }

    public void setCuntofftime(String cuntofftime) {
        this.cuntofftime = cuntofftime;
    }

    public String getZxAddress() {
        return zxAddress;
    }

    public void setZxAddress(String zxAddress) {
        this.zxAddress = zxAddress;
    }

    public String getPxYstype() {
        return pxYstype;
    }

    public void setPxYstype(String pxYstype) {
        this.pxYstype = pxYstype;
    }

    public String getPxAddress() {
        return pxAddress;
    }

    public void setPxAddress(String pxAddress) {
        this.pxAddress = pxAddress;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getIsConsolidation() {
        return IsConsolidation;
    }

    public void setIsConsolidation(String IsConsolidation) {
        this.IsConsolidation = IsConsolidation;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getIsEnabled() {
        return IsEnabled;
    }

    public void setIsEnabled(String IsEnabled) {
        this.IsEnabled = IsEnabled;
    }

    public String getIsReport() {
        return isReport;
    }

    public void setIsReport(String isReport) {
        this.isReport = isReport;
    }

    public Integer getFclCustoms() {
        return fclCustoms;
    }

    public void setFclCustoms(Integer fclCustoms) {
        this.fclCustoms = fclCustoms;
    }

    public Integer getFclCustomsNot() {
        return fclCustomsNot;
    }

    public void setFclCustomsNot(Integer fclCustomsNot) {
        this.fclCustomsNot = fclCustomsNot;
    }

    public Integer getLclCustoms() {
        return lclCustoms;
    }

    public void setLclCustoms(Integer lclCustoms) {
        this.lclCustoms = lclCustoms;
    }

    public Integer getLclCustomsNot() {
        return lclCustomsNot;
    }

    public void setLclCustomsNot(Integer lclCustomsNot) {
        this.lclCustomsNot = lclCustomsNot;
    }

    public String getFclCustomsTime() {
        return fclCustomsTime;
    }

    public void setFclCustomsTime(String fclCustomsTime) {
        this.fclCustomsTime = fclCustomsTime;
    }

    public String getFclCustomsNotTime() {
        return fclCustomsNotTime;
    }

    public void setFclCustomsNotTime(String fclCustomsNotTime) {
        this.fclCustomsNotTime = fclCustomsNotTime;
    }

    public String getLclCustomsTime() {
        return lclCustomsTime;
    }

    public void setLclCustomsTime(String lclCustomsTime) {
        this.lclCustomsTime = lclCustomsTime;
    }

    public String getLclCustomsNotTime() {
        return lclCustomsNotTime;
    }

    public void setLclCustomsNotTime(String lclCustomsNotTime) {
        this.lclCustomsNotTime = lclCustomsNotTime;
    }

    @Override
    public String toString() {
        return "BusiStation{" +
        "stationId=" + stationId +
        ", classCode=" + classCode +
        ", classSite=" + classSite +
        ", statioin=" + statioin +
        ", cuntofftime=" + cuntofftime +
        ", zxAddress=" + zxAddress +
        ", pxYstype=" + pxYstype +
        ", pxAddress=" + pxAddress +
        ", contacts=" + contacts +
        ", IsConsolidation=" + IsConsolidation +
        ", createdate=" + createdate +
        ", createuser=" + createuser +
        ", IsEnabled=" + IsEnabled +
        ", isReport=" + isReport +
        ", fclCustoms=" + fclCustoms +
        ", fclCustomsNot=" + fclCustomsNot +
        ", lclCustoms=" + lclCustoms +
        ", lclCustomsNot=" + lclCustomsNot +
        ", fclCustomsTime=" + fclCustomsTime +
        ", fclCustomsNotTime=" + fclCustomsNotTime +
        ", lclCustomsTime=" + lclCustomsTime +
        ", lclCustomsNotTime=" + lclCustomsNotTime +
        "}";
    }
}
