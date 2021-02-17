package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 中亚排舱表
 * </p>
 *
 * @author wsl123
 * @since 2020-01-11
 */
@TableName("busi_zy_info")
public class BusiZyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("cost_id")
    private String costId;
    @TableField("order_id")
    private String orderId;
    private String xianghao;
    private String xiangxing;
    @TableField("px_entry_date")
    private String pxEntryDate;
    @TableField("px_entry_time")
    private String pxEntryTime;
    @TableField("px_enter_car")
    private String pxEnterCar;
    @TableField("px_enter_lead_number")
    private String pxEnterLeadNumber;
    @TableField("xg_xiangkuang")
    private String xgXiangkuang;
    @TableField("xg_isrepair")
    private String xgIsrepair;
    @TableField("xg_ismingpai")
    private String xgIsmingpai;
    @TableField("xg_boat")
    private String xgBoat;
    @TableField("xg_send")
    private String xgSend;
    @TableField("xg_check")
    private String xgCheck;
    @TableField("road_isbill")
    private String roadIsbill;
    @TableField("road_check")
    private String roadCheck;
    private Date createdate;
    private String createuserid;
    private String createusername;
    @TableField("baoguan_docu")
    private String baoguanDocu;
    @TableField("suiche_docu")
    private String suicheDocu;
    private String waybill;
    private String paystate;
    @TableField("gd_consignee")
    private String gdConsignee;
    @TableField("gd_remark")
    private String gdRemark;
    @TableField("gd_check")
    private String gdCheck;
    @TableField("fayun_date")
    private Date fayunDate;
    @TableField("fayun_state")
    private String fayunState;
    @TableField("is_jg")
    private String isJg;
    @TableField("is_pz")
    private String isPz;
    @TableField("edit_remark")
    private String editRemark;
    @TableField("xg_xsphoto")
    private String xgXsphoto;
    @TableField("xg_xsstate")
    private String xgXsstate;
    @TableField("xh_check")
    private String xhCheck;
    private String isPlanedDelivery;
    private String bgcaodan;
    @TableField("bgcaodan_confirm")
    private String bgcaodanConfirm;
    @TableField("documents_give")
    private String documentsGive;
    @TableField("weight_Check")
    private String weightCheck;
    @TableField("customs_process")
    private String customsProcess;
    private String bgremark;
    @TableField("classzy_no")
    private String classzyNo;
    @TableField("jz2_remark")
    private String jz2Remark;
    @TableField("rc_weight")
    private String rcWeight;
    @TableField("jz_weight")
    private String jzWeight;
    @TableField("car_number")
    private String carNumber;
    private String jz2data;
    @TableField("zhengben_give")
    private String zhengbenGive;
    @TableField("yichang_date")
    private Date yichangDate;
    private Date roadtime;
    private Date gdtime;
    private Date xgtime;
    @TableField("Real_unloadSite")
    private String realUnloadsite;
    @TableField("mingpai_gh")
    private String mingpaiGh;
    @TableField("cepian_result")
    private String cepianResult;
    @TableField("transport_Dept")
    private String transportDept;
    @TableField("ISJZ_LDbus")
    private String isjzLdbus;
    @TableField("cause_nojz")
    private String causeNojz;
    @TableField("roadlog_remark")
    private String roadlogRemark;
    @TableField("Actual_classbh")
    private String actualClassbh;
    @TableField("xianghao_place")
    private String xianghaoPlace;
    @TableField("is_applynum")
    private String isApplynum;
    @TableField("container_kgs")
    private Integer containerKgs;

    /** 货物状态表id */
    @TableField("track_id")
    private Long trackId;
    /** 托书编号 */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 删除标志0正常1删除
     */
    @TableField("del_flag")
    private String delFlag;


    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getXianghao() {
        return xianghao;
    }

    public void setXianghao(String xianghao) {
        this.xianghao = xianghao;
    }

    public String getXiangxing() {
        return xiangxing;
    }

    public void setXiangxing(String xiangxing) {
        this.xiangxing = xiangxing;
    }

    public String getPxEntryDate() {
        return pxEntryDate;
    }

    public void setPxEntryDate(String pxEntryDate) {
        this.pxEntryDate = pxEntryDate;
    }

    public String getPxEntryTime() {
        return pxEntryTime;
    }

    public void setPxEntryTime(String pxEntryTime) {
        this.pxEntryTime = pxEntryTime;
    }

    public String getPxEnterCar() {
        return pxEnterCar;
    }

    public void setPxEnterCar(String pxEnterCar) {
        this.pxEnterCar = pxEnterCar;
    }

    public String getPxEnterLeadNumber() {
        return pxEnterLeadNumber;
    }

    public void setPxEnterLeadNumber(String pxEnterLeadNumber) {
        this.pxEnterLeadNumber = pxEnterLeadNumber;
    }

    public String getXgXiangkuang() {
        return xgXiangkuang;
    }

    public void setXgXiangkuang(String xgXiangkuang) {
        this.xgXiangkuang = xgXiangkuang;
    }

    public String getXgIsrepair() {
        return xgIsrepair;
    }

    public void setXgIsrepair(String xgIsrepair) {
        this.xgIsrepair = xgIsrepair;
    }

    public String getXgIsmingpai() {
        return xgIsmingpai;
    }

    public void setXgIsmingpai(String xgIsmingpai) {
        this.xgIsmingpai = xgIsmingpai;
    }

    public String getXgBoat() {
        return xgBoat;
    }

    public void setXgBoat(String xgBoat) {
        this.xgBoat = xgBoat;
    }

    public String getXgSend() {
        return xgSend;
    }

    public void setXgSend(String xgSend) {
        this.xgSend = xgSend;
    }

    public String getXgCheck() {
        return xgCheck;
    }

    public void setXgCheck(String xgCheck) {
        this.xgCheck = xgCheck;
    }

    public String getRoadIsbill() {
        return roadIsbill;
    }

    public void setRoadIsbill(String roadIsbill) {
        this.roadIsbill = roadIsbill;
    }

    public String getRoadCheck() {
        return roadCheck;
    }

    public void setRoadCheck(String roadCheck) {
        this.roadCheck = roadCheck;
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

    public String getBaoguanDocu() {
        return baoguanDocu;
    }

    public void setBaoguanDocu(String baoguanDocu) {
        this.baoguanDocu = baoguanDocu;
    }

    public String getSuicheDocu() {
        return suicheDocu;
    }

    public void setSuicheDocu(String suicheDocu) {
        this.suicheDocu = suicheDocu;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getPaystate() {
        return paystate;
    }

    public void setPaystate(String paystate) {
        this.paystate = paystate;
    }

    public String getGdConsignee() {
        return gdConsignee;
    }

    public void setGdConsignee(String gdConsignee) {
        this.gdConsignee = gdConsignee;
    }

    public String getGdRemark() {
        return gdRemark;
    }

    public void setGdRemark(String gdRemark) {
        this.gdRemark = gdRemark;
    }

    public String getGdCheck() {
        return gdCheck;
    }

    public void setGdCheck(String gdCheck) {
        this.gdCheck = gdCheck;
    }

    public Date getFayunDate() {
        return fayunDate;
    }

    public void setFayunDate(Date fayunDate) {
        this.fayunDate = fayunDate;
    }

    public String getFayunState() {
        return fayunState;
    }

    public void setFayunState(String fayunState) {
        this.fayunState = fayunState;
    }

    public String getIsJg() {
        return isJg;
    }

    public void setIsJg(String isJg) {
        this.isJg = isJg;
    }

    public String getIsPz() {
        return isPz;
    }

    public void setIsPz(String isPz) {
        this.isPz = isPz;
    }

    public String getEditRemark() {
        return editRemark;
    }

    public void setEditRemark(String editRemark) {
        this.editRemark = editRemark;
    }

    public String getXgXsphoto() {
        return xgXsphoto;
    }

    public void setXgXsphoto(String xgXsphoto) {
        this.xgXsphoto = xgXsphoto;
    }

    public String getXgXsstate() {
        return xgXsstate;
    }

    public void setXgXsstate(String xgXsstate) {
        this.xgXsstate = xgXsstate;
    }

    public String getXhCheck() {
        return xhCheck;
    }

    public void setXhCheck(String xhCheck) {
        this.xhCheck = xhCheck;
    }

    public String getIsPlanedDelivery() {
        return isPlanedDelivery;
    }

    public void setIsPlanedDelivery(String isPlanedDelivery) {
        this.isPlanedDelivery = isPlanedDelivery;
    }

    public String getBgcaodan() {
        return bgcaodan;
    }

    public void setBgcaodan(String bgcaodan) {
        this.bgcaodan = bgcaodan;
    }

    public String getBgcaodanConfirm() {
        return bgcaodanConfirm;
    }

    public void setBgcaodanConfirm(String bgcaodanConfirm) {
        this.bgcaodanConfirm = bgcaodanConfirm;
    }

    public String getDocumentsGive() {
        return documentsGive;
    }

    public void setDocumentsGive(String documentsGive) {
        this.documentsGive = documentsGive;
    }

    public String getWeightCheck() {
        return weightCheck;
    }

    public void setWeightCheck(String weightCheck) {
        this.weightCheck = weightCheck;
    }

    public String getCustomsProcess() {
        return customsProcess;
    }

    public void setCustomsProcess(String customsProcess) {
        this.customsProcess = customsProcess;
    }

    public String getBgremark() {
        return bgremark;
    }

    public void setBgremark(String bgremark) {
        this.bgremark = bgremark;
    }

    public String getClasszyNo() {
        return classzyNo;
    }

    public void setClasszyNo(String classzyNo) {
        this.classzyNo = classzyNo;
    }

    public String getJz2Remark() {
        return jz2Remark;
    }

    public void setJz2Remark(String jz2Remark) {
        this.jz2Remark = jz2Remark;
    }

    public String getRcWeight() {
        return rcWeight;
    }

    public void setRcWeight(String rcWeight) {
        this.rcWeight = rcWeight;
    }

    public String getJzWeight() {
        return jzWeight;
    }

    public void setJzWeight(String jzWeight) {
        this.jzWeight = jzWeight;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getJz2data() {
        return jz2data;
    }

    public void setJz2data(String jz2data) {
        this.jz2data = jz2data;
    }

    public String getZhengbenGive() {
        return zhengbenGive;
    }

    public void setZhengbenGive(String zhengbenGive) {
        this.zhengbenGive = zhengbenGive;
    }

    public Date getYichangDate() {
        return yichangDate;
    }

    public void setYichangDate(Date yichangDate) {
        this.yichangDate = yichangDate;
    }

    public Date getRoadtime() {
        return roadtime;
    }

    public void setRoadtime(Date roadtime) {
        this.roadtime = roadtime;
    }

    public Date getGdtime() {
        return gdtime;
    }

    public void setGdtime(Date gdtime) {
        this.gdtime = gdtime;
    }

    public Date getXgtime() {
        return xgtime;
    }

    public void setXgtime(Date xgtime) {
        this.xgtime = xgtime;
    }

    public String getRealUnloadsite() {
        return realUnloadsite;
    }

    public void setRealUnloadsite(String realUnloadsite) {
        this.realUnloadsite = realUnloadsite;
    }

    public String getMingpaiGh() {
        return mingpaiGh;
    }

    public void setMingpaiGh(String mingpaiGh) {
        this.mingpaiGh = mingpaiGh;
    }

    public String getCepianResult() {
        return cepianResult;
    }

    public void setCepianResult(String cepianResult) {
        this.cepianResult = cepianResult;
    }

    public String getTransportDept() {
        return transportDept;
    }

    public void setTransportDept(String transportDept) {
        this.transportDept = transportDept;
    }

    public String getIsjzLdbus() {
        return isjzLdbus;
    }

    public void setIsjzLdbus(String isjzLdbus) {
        this.isjzLdbus = isjzLdbus;
    }

    public String getCauseNojz() {
        return causeNojz;
    }

    public void setCauseNojz(String causeNojz) {
        this.causeNojz = causeNojz;
    }

    public String getRoadlogRemark() {
        return roadlogRemark;
    }

    public void setRoadlogRemark(String roadlogRemark) {
        this.roadlogRemark = roadlogRemark;
    }

    public String getActualClassbh() {
        return actualClassbh;
    }

    public void setActualClassbh(String actualClassbh) {
        this.actualClassbh = actualClassbh;
    }

    public String getXianghaoPlace() {
        return xianghaoPlace;
    }

    public void setXianghaoPlace(String xianghaoPlace) {
        this.xianghaoPlace = xianghaoPlace;
    }

    public String getIsApplynum() {
        return isApplynum;
    }

    public void setIsApplynum(String isApplynum) {
        this.isApplynum = isApplynum;
    }

    public Integer getContainerKgs() {
        return containerKgs;
    }

    public void setContainerKgs(Integer containerKgs) {
        this.containerKgs = containerKgs;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BusiZyInfo{" +
        "costId=" + costId +
        ", orderId=" + orderId +
        ", xianghao=" + xianghao +
        ", xiangxing=" + xiangxing +
        ", pxEntryDate=" + pxEntryDate +
        ", pxEntryTime=" + pxEntryTime +
        ", pxEnterCar=" + pxEnterCar +
        ", pxEnterLeadNumber=" + pxEnterLeadNumber +
        ", xgXiangkuang=" + xgXiangkuang +
        ", xgIsrepair=" + xgIsrepair +
        ", xgIsmingpai=" + xgIsmingpai +
        ", xgBoat=" + xgBoat +
        ", xgSend=" + xgSend +
        ", xgCheck=" + xgCheck +
        ", roadIsbill=" + roadIsbill +
        ", roadCheck=" + roadCheck +
        ", createdate=" + createdate +
        ", createuserid=" + createuserid +
        ", createusername=" + createusername +
        ", baoguanDocu=" + baoguanDocu +
        ", suicheDocu=" + suicheDocu +
        ", waybill=" + waybill +
        ", paystate=" + paystate +
        ", gdConsignee=" + gdConsignee +
        ", gdRemark=" + gdRemark +
        ", gdCheck=" + gdCheck +
        ", fayunDate=" + fayunDate +
        ", fayunState=" + fayunState +
        ", isJg=" + isJg +
        ", isPz=" + isPz +
        ", editRemark=" + editRemark +
        ", xgXsphoto=" + xgXsphoto +
        ", xgXsstate=" + xgXsstate +
        ", xhCheck=" + xhCheck +
        ", isPlanedDelivery=" + isPlanedDelivery +
        ", bgcaodan=" + bgcaodan +
        ", bgcaodanConfirm=" + bgcaodanConfirm +
        ", documentsGive=" + documentsGive +
        ", weightCheck=" + weightCheck +
        ", customsProcess=" + customsProcess +
        ", bgremark=" + bgremark +
        ", classzyNo=" + classzyNo +
        ", jz2Remark=" + jz2Remark +
        ", rcWeight=" + rcWeight +
        ", jzWeight=" + jzWeight +
        ", carNumber=" + carNumber +
        ", jz2data=" + jz2data +
        ", zhengbenGive=" + zhengbenGive +
        ", yichangDate=" + yichangDate +
        ", roadtime=" + roadtime +
        ", gdtime=" + gdtime +
        ", xgtime=" + xgtime +
        ", realUnloadsite=" + realUnloadsite +
        ", mingpaiGh=" + mingpaiGh +
        ", cepianResult=" + cepianResult +
        ", transportDept=" + transportDept +
        ", isjzLdbus=" + isjzLdbus +
        ", causeNojz=" + causeNojz +
        ", roadlogRemark=" + roadlogRemark +
        ", actualClassbh=" + actualClassbh +
        ", xianghaoPlace=" + xianghaoPlace +
        ", isApplynum=" + isApplynum +
        ", containerKgs=" + containerKgs +
        "}";
    }
}
