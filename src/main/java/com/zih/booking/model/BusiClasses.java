package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 班列表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@TableName("busi_classes")
public class BusiClasses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班列id，主键
     */
    @TableId(value = "class_ID", type = IdType.AUTO)
    private String classId;
    /**
     * 班列编号
     */
    @TableField("class_bh")
    private String classBh;
    /**
     * 线路类型：0是中欧2是中亚3是中越4是中俄
     */
    @TableField("line_typeid")
    private Integer lineTypeid;
    /**
     * 线路ID
     */
    @TableField("line_id")
    private String lineId;
    /**
     * 班列模板ID
     */
    @TableField("class_t_ID")
    private String classTId;
    /**
     * 班列号
     */
    @TableField("class_number")
    private String classNumber;
    /**
     * 开行班列
     */
    @TableField("class_BlockTrain")
    private String classBlocktrain;
    /**
     * 班列类型
     */
    @TableField("class_ClassType")
    private String classClasstype;
    /**
     * 0为去(西向) 1为回(东向）
     */
    @TableField("class_EastAndWest")
    private String classEastandwest;
    /**
     * 始发站编号
     */
    @TableField("class_StationOfDeparture")
    private String classStationofdeparture;
    /**
     * 目的站编号
     */
    @TableField("class_StationOfDestination")
    private String classStationofdestination;

    /** 始发站名称 其实没啥用 与平台端保持同步*/
    @TableField(exist=false)
    private String classStationofdepartureName;

    /** 目的站名称 */
    @TableField(exist=false)
    private String classStationofdestinationName;

    public String getClassStationofdepartureName() {
        return classStationofdepartureName;
    }

    public void setClassStationofdepartureName(String classStationofdepartureName) {
        this.classStationofdepartureName = classStationofdepartureName;
    }

    public String getClassStationofdestinationName() {
        return classStationofdestinationName;
    }

    public void setClassStationofdestinationName(String classStationofdestinationName) {
        this.classStationofdestinationName = classStationofdestinationName;
    }

    /**
     * 运行时间
     */
    @TableField("class_TransportTime")
    private String classTransporttime;
    /**
     * 班列是否接受申请(是否发布) 1是 0否
     */
    private String isPublicly;
    /**
     * 舱位总数
     */
    @TableField("class_spacenumber")
    private Integer classSpacenumber;
    /**
     * 整箱舱位数
     */
    private Integer zxcnt;
    /**
     * 拼箱状态（0是未满1是已满）
     */
    private String pxstate;
    /**
     * 拼箱体积数
     */
    private Integer pxcnt;
    /**
     * 拼箱重量数
     */
    private Integer pxkgs;
    /**
     * 计划开车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("class_STime")
    private Date classStime;
    /**
     * 计划到站时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("class_ETime")
    private Date classEtime;
    /**
     * 车辆状态：0未开车 1已开车 2已到终点站3取消班列4中亚实际发运班列
     */
    @TableField("class_state")
    private String classState;
    /**
     * 整柜随机接货站点编号
     */
    @TableField("receive_site_full")
    private String receiveSiteFull;
    /**
     * 拼箱随机接货站点编号
     */
    @TableField("receive_site_lcl")
    private String receiveSiteLcl;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;
    /**
     * 创建用户ID
     */
    private String createuserid;
    /**
     * 创建用户姓名
     */
    private String createusername;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 费用是否可修改 0可修改 1不可修改（默认可修改）
     */
    @TableField("CostEntry_State")
    private Integer costentryState;
    /**
     * 当天录运踪的时间（每天都在更新）
     */
    @TableField("Distribution_time")
    private String distributionTime;
    /**
     * 控制结算模块箱号录入节点 0可修改 1不可修改（默认可修改）
     */
    @TableField("XHEntry_State")
    private Integer xhentryState;
    /**
     * 管理部控制拼箱录入拼箱体积表0是不可修改，1是可修改（默认不可修改）
     */
    @TableField("PXEntry_State")
    private Integer pxentryState;
    /**
     * 美元转人民币
     */
    private BigDecimal USDtoRMBe;
    /**
     * 欧元转人民币
     */
    private BigDecimal EURtoRMBe;
    /**
     * 欧元转美金
     */
    private BigDecimal EUTtoUSDe;
    /**
     * 卢布转人民币
     */
    private BigDecimal RURtoRMBe;


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassBh() {
        return classBh;
    }

    public void setClassBh(String classBh) {
        this.classBh = classBh;
    }

    public Integer getLineTypeid() {
        return lineTypeid;
    }

    public void setLineTypeid(Integer lineTypeid) {
        this.lineTypeid = lineTypeid;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getClassTId() {
        return classTId;
    }

    public void setClassTId(String classTId) {
        this.classTId = classTId;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassBlocktrain() {
        return classBlocktrain;
    }

    public void setClassBlocktrain(String classBlocktrain) {
        this.classBlocktrain = classBlocktrain;
    }

    public String getClassClasstype() {
        return classClasstype;
    }

    public void setClassClasstype(String classClasstype) {
        this.classClasstype = classClasstype;
    }

    public String getClassEastandwest() {
        return classEastandwest;
    }

    public void setClassEastandwest(String classEastandwest) {
        this.classEastandwest = classEastandwest;
    }

    public String getClassStationofdeparture() {
        return classStationofdeparture;
    }

    public void setClassStationofdeparture(String classStationofdeparture) {
        this.classStationofdeparture = classStationofdeparture;
    }

    public String getClassStationofdestination() {
        return classStationofdestination;
    }

    public void setClassStationofdestination(String classStationofdestination) {
        this.classStationofdestination = classStationofdestination;
    }

    public String getClassTransporttime() {
        return classTransporttime;
    }

    public void setClassTransporttime(String classTransporttime) {
        this.classTransporttime = classTransporttime;
    }

    public String getIsPublicly() {
        return isPublicly;
    }

    public void setIsPublicly(String isPublicly) {
        this.isPublicly = isPublicly;
    }

    public Integer getClassSpacenumber() {
        return classSpacenumber;
    }

    public void setClassSpacenumber(Integer classSpacenumber) {
        this.classSpacenumber = classSpacenumber;
    }

    public Integer getZxcnt() {
        return zxcnt;
    }

    public void setZxcnt(Integer zxcnt) {
        this.zxcnt = zxcnt;
    }

    public String getPxstate() {
        return pxstate;
    }

    public void setPxstate(String pxstate) {
        this.pxstate = pxstate;
    }

    public Integer getPxcnt() {
        return pxcnt;
    }

    public void setPxcnt(Integer pxcnt) {
        this.pxcnt = pxcnt;
    }

    public Integer getPxkgs() {
        return pxkgs;
    }

    public void setPxkgs(Integer pxkgs) {
        this.pxkgs = pxkgs;
    }

    public Date getClassStime() {
        return classStime;
    }

    public void setClassStime(Date classStime) {
        this.classStime = classStime;
    }

    public Date getClassEtime() {
        return classEtime;
    }

    public void setClassEtime(Date classEtime) {
        this.classEtime = classEtime;
    }

    public String getClassState() {
        return classState;
    }

    public void setClassState(String classState) {
        this.classState = classState;
    }

    public String getReceiveSiteFull() {
        return receiveSiteFull;
    }

    public void setReceiveSiteFull(String receiveSiteFull) {
        this.receiveSiteFull = receiveSiteFull;
    }

    public String getReceiveSiteLcl() {
        return receiveSiteLcl;
    }

    public void setReceiveSiteLcl(String receiveSiteLcl) {
        this.receiveSiteLcl = receiveSiteLcl;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCostentryState() {
        return costentryState;
    }

    public void setCostentryState(Integer costentryState) {
        this.costentryState = costentryState;
    }

    public String getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(String distributionTime) {
        this.distributionTime = distributionTime;
    }

    public Integer getXhentryState() {
        return xhentryState;
    }

    public void setXhentryState(Integer xhentryState) {
        this.xhentryState = xhentryState;
    }

    public Integer getPxentryState() {
        return pxentryState;
    }

    public void setPxentryState(Integer pxentryState) {
        this.pxentryState = pxentryState;
    }

    public BigDecimal getUSDtoRMBe() {
        return USDtoRMBe;
    }

    public void setUSDtoRMBe(BigDecimal USDtoRMBe) {
        this.USDtoRMBe = USDtoRMBe;
    }

    public BigDecimal getEURtoRMBe() {
        return EURtoRMBe;
    }

    public void setEURtoRMBe(BigDecimal EURtoRMBe) {
        this.EURtoRMBe = EURtoRMBe;
    }

    public BigDecimal getEUTtoUSDe() {
        return EUTtoUSDe;
    }

    public void setEUTtoUSDe(BigDecimal EUTtoUSDe) {
        this.EUTtoUSDe = EUTtoUSDe;
    }

    public BigDecimal getRURtoRMBe() {
        return RURtoRMBe;
    }

    public void setRURtoRMBe(BigDecimal RURtoRMBe) {
        this.RURtoRMBe = RURtoRMBe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusiClasses classes=(BusiClasses) o;
        return Objects.equals(classId, classes.getClassId());
    }


    @Override
    public String toString() {
        return "BusiClasses{" +
        "classId=" + classId +
        ", classBh=" + classBh +
        ", lineTypeid=" + lineTypeid +
        ", lineId=" + lineId +
        ", classTId=" + classTId +
        ", classNumber=" + classNumber +
        ", classBlocktrain=" + classBlocktrain +
        ", classClasstype=" + classClasstype +
        ", classEastandwest=" + classEastandwest +
        ", classStationofdeparture=" + classStationofdeparture +
        ", classStationofdestination=" + classStationofdestination +
        ", classTransporttime=" + classTransporttime +
        ", isPublicly=" + isPublicly +
        ", classSpacenumber=" + classSpacenumber +
        ", zxcnt=" + zxcnt +
        ", pxstate=" + pxstate +
        ", pxcnt=" + pxcnt +
        ", pxkgs=" + pxkgs +
        ", classStime=" + classStime +
        ", classEtime=" + classEtime +
        ", classState=" + classState +
        ", receiveSiteFull=" + receiveSiteFull +
        ", receiveSiteLcl=" + receiveSiteLcl +
        ", createdate=" + createdate +
        ", createuserid=" + createuserid +
        ", createusername=" + createusername +
        ", remark=" + remark +
        ", costentryState=" + costentryState +
        ", distributionTime=" + distributionTime +
        ", xhentryState=" + xhentryState +
        ", pxentryState=" + pxentryState +
        ", USDtoRMBe=" + USDtoRMBe +
        ", EURtoRMBe=" + EURtoRMBe +
        ", EUTtoUSDe=" + EUTtoUSDe +
        ", RURtoRMBe=" + RURtoRMBe +
        "}";
    }
}
