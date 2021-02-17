package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2020-03-17
 */
@TableName("Busi_Taizang_Info")
public class BusiTaizangInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 托书order_id
     */
    @TableField("ID")
    private String id;
    /**
     * 美金合计
     */
    private String Totalusd;
    /**
     * 业务部反馈状态，默认0，有异议1
     */
    @TableField("YW_Feedback")
    private Integer ywFeedback;
    /**
     * 费用确认单确认状态0未确认，1已确认
     */
    @TableField("Cost_verify")
    private String costVerify;
    /**
     * 发票单号
     */
    @TableField("bill_num1")
    private String billNum1;
    /**
     * 发票单号
     */
    @TableField("bill_num2")
    private String billNum2;
    /**
     * 发票单号
     */
    @TableField("bill_num3")
    private String billNum3;
    /**
     * 默认为0未审核，1为审核通过，2为审核失败
     */
    @TableField("discounts_state")
    private String discountsState;
    /**
     * 业务部异常费用
     */
    @TableField("Abnormal_cost")
    private String abnormalCost;
    /**
     * 客户开票时的要求
     */
    @TableField("Remark_kp")
    private String remarkKp;
    /**
     * 业务部异常费用
     */
    @TableField("Abnormal_costbz")
    private String abnormalCostbz;
    /**
     * 返利使用方
     */
    @TableField("discounts_user")
    private String discountsUser;
    /**
     * 业务审核通过费用确认单时间
     */
    private Date FeeCheckedTime;
    /**
     * 挂账备注
     */
    @TableField("guazhang_remark")
    private String guazhangRemark;
    /**
     * 到付费用
     */
    @TableField("daofu_fee")
    private Double daofuFee;
    /**
     * 客户前台费用确认单显示其他费用备注的权限，0是显示，1不显示
     */
    private String ISRemark;
    /**
     * 客户确认费用邮箱
     */
    @TableField("fy_email")
    private String fyEmail;
    @TableField("bill_link2")
    private String billLink2;
    @TableField("SH_INCOMES")
    private String shIncomes;
    @TableField("discounts_time")
    private Date discountsTime;
    @TableField("taizang_fp_no")
    private String taizangFpNo;
    @TableField("taizang_Operatetior")
    private String taizangOperatetior;
    @TableField("pay_state")
    private String payState;
    @TableField("discounts_month")
    private String discountsMonth;
    private String USDtoRMB;
    @TableField("taizang_kp_rmb")
    private String taizangKpRmb;
    private String RMBTotalS;
    @TableField("kp_usd")
    private String kpUsd;
    @TableField("taizang_pay_date")
    private String taizangPayDate;
    private String USDTotal;
    @TableField("chargeUpload_day")
    private Date chargeuploadDay;
    @TableField("taizang_other_income")
    private String taizangOtherIncome;
    @TableField("BG_INCOMES")
    private String bgIncomes;
    @TableField("taizang_kp_usd")
    private String taizangKpUsd;
    private String RMBTotal;
    @TableField("bill_state2")
    private String billState2;
    @TableField("confirmation_date")
    private String confirmationDate;
    private String overweightcost;
    private String bxcostfund;
    private String EURTotalS;
    @TableId("Taizang_id")
    private String taizangId;
    @TableField("taizang_fp_date")
    private String taizangFpDate;
    @TableField("bill_number")
    private String billNumber;
    @TableField("paychange_link")
    private String paychangeLink;
    @TableField("post_date")
    private String postDate;
    @TableField("other_income")
    private String otherIncome;
    @TableField("kp_eur")
    private String kpEur;
    @TableField("taizang_dp_date")
    private String taizangDpDate;
    @TableField("taizang_exRemark")
    private String taizangExremark;
    @TableField("taizang_IsOverdue")
    private String taizangIsoverdue;
    private String Totalmoney;
    @TableField("paychange_state")
    private String paychangeState;
    @TableField("PX_roadincome")
    private String pxRoadincome;
    @TableField("daofu_feeCurrency")
    private String daofuFeecurrency;
    @TableField("HUANXIANG")
    private String huanxiang;
    @TableField("ISsign_agreemen")
    private String issignAgreemen;
    @TableField("bill_link3")
    private String billLink3;
    private String TotalmoneyS;
    @TableField("taizang_OperationTime")
    private Date taizangOperationtime;
    @TableField("bill_state3")
    private String billState3;
    private Double discounts;
    @TableField("pay_link")
    private String payLink;
    private String EURTotal;
    private String EURtoRMB;
    @TableField("kp_rmb")
    private String kpRmb;
    private String USDTotalS;
    @TableField("Remark_other_income")
    private String remarkOtherIncome;
    @TableField("bill_state")
    private String billState;
    @TableField("taizang_pay")
    private String taizangPay;
    @TableField("taizang_EditSymbol")
    private String taizangEditsymbol;
    private String EUTtoUSD;
    @TableField("bill_link1")
    private String billLink1;
    @TableField("taizang_kp_eur")
    private String taizangKpEur;
    @TableField("TH_INCOMES")
    private String thIncomes;
    @TableField("bill_state1")
    private String billState1;
    @TableField("Remark_fanli")
    private String remarkFanli;
    @TableField("taizang_remark")
    private String taizangRemark;
    @TableField("Pinup_cost")
    private String pinupCost;
    @TableField("px_Settlement_volumet")
    private String pxSettlementVolumet;
    private String siteIncome;
    @TableField("client_grade")
    private String clientGrade;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalusd() {
        return Totalusd;
    }

    public void setTotalusd(String Totalusd) {
        this.Totalusd = Totalusd;
    }

    public Integer getYwFeedback() {
        return ywFeedback;
    }

    public void setYwFeedback(Integer ywFeedback) {
        this.ywFeedback = ywFeedback;
    }

    public String getCostVerify() {
        return costVerify;
    }

    public void setCostVerify(String costVerify) {
        this.costVerify = costVerify;
    }

    public String getBillNum1() {
        return billNum1;
    }

    public void setBillNum1(String billNum1) {
        this.billNum1 = billNum1;
    }

    public String getBillNum2() {
        return billNum2;
    }

    public void setBillNum2(String billNum2) {
        this.billNum2 = billNum2;
    }

    public String getBillNum3() {
        return billNum3;
    }

    public void setBillNum3(String billNum3) {
        this.billNum3 = billNum3;
    }

    public String getDiscountsState() {
        return discountsState;
    }

    public void setDiscountsState(String discountsState) {
        this.discountsState = discountsState;
    }

    public String getAbnormalCost() {
        return abnormalCost;
    }

    public void setAbnormalCost(String abnormalCost) {
        this.abnormalCost = abnormalCost;
    }

    public String getRemarkKp() {
        return remarkKp;
    }

    public void setRemarkKp(String remarkKp) {
        this.remarkKp = remarkKp;
    }

    public String getAbnormalCostbz() {
        return abnormalCostbz;
    }

    public void setAbnormalCostbz(String abnormalCostbz) {
        this.abnormalCostbz = abnormalCostbz;
    }

    public String getDiscountsUser() {
        return discountsUser;
    }

    public void setDiscountsUser(String discountsUser) {
        this.discountsUser = discountsUser;
    }

    public Date getFeeCheckedTime() {
        return FeeCheckedTime;
    }

    public void setFeeCheckedTime(Date FeeCheckedTime) {
        this.FeeCheckedTime = FeeCheckedTime;
    }

    public String getGuazhangRemark() {
        return guazhangRemark;
    }

    public void setGuazhangRemark(String guazhangRemark) {
        this.guazhangRemark = guazhangRemark;
    }

    public Double getDaofuFee() {
        return daofuFee;
    }

    public void setDaofuFee(Double daofuFee) {
        this.daofuFee = daofuFee;
    }

    public String getISRemark() {
        return ISRemark;
    }

    public void setISRemark(String ISRemark) {
        this.ISRemark = ISRemark;
    }

    public String getFyEmail() {
        return fyEmail;
    }

    public void setFyEmail(String fyEmail) {
        this.fyEmail = fyEmail;
    }

    public String getBillLink2() {
        return billLink2;
    }

    public void setBillLink2(String billLink2) {
        this.billLink2 = billLink2;
    }

    public String getShIncomes() {
        return shIncomes;
    }

    public void setShIncomes(String shIncomes) {
        this.shIncomes = shIncomes;
    }

    public Date getDiscountsTime() {
        return discountsTime;
    }

    public void setDiscountsTime(Date discountsTime) {
        this.discountsTime = discountsTime;
    }

    public String getTaizangFpNo() {
        return taizangFpNo;
    }

    public void setTaizangFpNo(String taizangFpNo) {
        this.taizangFpNo = taizangFpNo;
    }

    public String getTaizangOperatetior() {
        return taizangOperatetior;
    }

    public void setTaizangOperatetior(String taizangOperatetior) {
        this.taizangOperatetior = taizangOperatetior;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getDiscountsMonth() {
        return discountsMonth;
    }

    public void setDiscountsMonth(String discountsMonth) {
        this.discountsMonth = discountsMonth;
    }

    public String getUSDtoRMB() {
        return USDtoRMB;
    }

    public void setUSDtoRMB(String USDtoRMB) {
        this.USDtoRMB = USDtoRMB;
    }

    public String getTaizangKpRmb() {
        return taizangKpRmb;
    }

    public void setTaizangKpRmb(String taizangKpRmb) {
        this.taizangKpRmb = taizangKpRmb;
    }

    public String getRMBTotalS() {
        return RMBTotalS;
    }

    public void setRMBTotalS(String RMBTotalS) {
        this.RMBTotalS = RMBTotalS;
    }

    public String getKpUsd() {
        return kpUsd;
    }

    public void setKpUsd(String kpUsd) {
        this.kpUsd = kpUsd;
    }

    public String getTaizangPayDate() {
        return taizangPayDate;
    }

    public void setTaizangPayDate(String taizangPayDate) {
        this.taizangPayDate = taizangPayDate;
    }

    public String getUSDTotal() {
        return USDTotal;
    }

    public void setUSDTotal(String USDTotal) {
        this.USDTotal = USDTotal;
    }

    public Date getChargeuploadDay() {
        return chargeuploadDay;
    }

    public void setChargeuploadDay(Date chargeuploadDay) {
        this.chargeuploadDay = chargeuploadDay;
    }

    public String getTaizangOtherIncome() {
        return taizangOtherIncome;
    }

    public void setTaizangOtherIncome(String taizangOtherIncome) {
        this.taizangOtherIncome = taizangOtherIncome;
    }

    public String getBgIncomes() {
        return bgIncomes;
    }

    public void setBgIncomes(String bgIncomes) {
        this.bgIncomes = bgIncomes;
    }

    public String getTaizangKpUsd() {
        return taizangKpUsd;
    }

    public void setTaizangKpUsd(String taizangKpUsd) {
        this.taizangKpUsd = taizangKpUsd;
    }

    public String getRMBTotal() {
        return RMBTotal;
    }

    public void setRMBTotal(String RMBTotal) {
        this.RMBTotal = RMBTotal;
    }

    public String getBillState2() {
        return billState2;
    }

    public void setBillState2(String billState2) {
        this.billState2 = billState2;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getOverweightcost() {
        return overweightcost;
    }

    public void setOverweightcost(String overweightcost) {
        this.overweightcost = overweightcost;
    }

    public String getBxcostfund() {
        return bxcostfund;
    }

    public void setBxcostfund(String bxcostfund) {
        this.bxcostfund = bxcostfund;
    }

    public String getEURTotalS() {
        return EURTotalS;
    }

    public void setEURTotalS(String EURTotalS) {
        this.EURTotalS = EURTotalS;
    }

    public String getTaizangId() {
        return taizangId;
    }

    public void setTaizangId(String taizangId) {
        this.taizangId = taizangId;
    }

    public String getTaizangFpDate() {
        return taizangFpDate;
    }

    public void setTaizangFpDate(String taizangFpDate) {
        this.taizangFpDate = taizangFpDate;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getPaychangeLink() {
        return paychangeLink;
    }

    public void setPaychangeLink(String paychangeLink) {
        this.paychangeLink = paychangeLink;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome;
    }

    public String getKpEur() {
        return kpEur;
    }

    public void setKpEur(String kpEur) {
        this.kpEur = kpEur;
    }

    public String getTaizangDpDate() {
        return taizangDpDate;
    }

    public void setTaizangDpDate(String taizangDpDate) {
        this.taizangDpDate = taizangDpDate;
    }

    public String getTaizangExremark() {
        return taizangExremark;
    }

    public void setTaizangExremark(String taizangExremark) {
        this.taizangExremark = taizangExremark;
    }

    public String getTaizangIsoverdue() {
        return taizangIsoverdue;
    }

    public void setTaizangIsoverdue(String taizangIsoverdue) {
        this.taizangIsoverdue = taizangIsoverdue;
    }

    public String getTotalmoney() {
        return Totalmoney;
    }

    public void setTotalmoney(String Totalmoney) {
        this.Totalmoney = Totalmoney;
    }

    public String getPaychangeState() {
        return paychangeState;
    }

    public void setPaychangeState(String paychangeState) {
        this.paychangeState = paychangeState;
    }

    public String getPxRoadincome() {
        return pxRoadincome;
    }

    public void setPxRoadincome(String pxRoadincome) {
        this.pxRoadincome = pxRoadincome;
    }

    public String getDaofuFeecurrency() {
        return daofuFeecurrency;
    }

    public void setDaofuFeecurrency(String daofuFeecurrency) {
        this.daofuFeecurrency = daofuFeecurrency;
    }

    public String getHuanxiang() {
        return huanxiang;
    }

    public void setHuanxiang(String huanxiang) {
        this.huanxiang = huanxiang;
    }

    public String getIssignAgreemen() {
        return issignAgreemen;
    }

    public void setIssignAgreemen(String issignAgreemen) {
        this.issignAgreemen = issignAgreemen;
    }

    public String getBillLink3() {
        return billLink3;
    }

    public void setBillLink3(String billLink3) {
        this.billLink3 = billLink3;
    }

    public String getTotalmoneyS() {
        return TotalmoneyS;
    }

    public void setTotalmoneyS(String TotalmoneyS) {
        this.TotalmoneyS = TotalmoneyS;
    }

    public Date getTaizangOperationtime() {
        return taizangOperationtime;
    }

    public void setTaizangOperationtime(Date taizangOperationtime) {
        this.taizangOperationtime = taizangOperationtime;
    }

    public String getBillState3() {
        return billState3;
    }

    public void setBillState3(String billState3) {
        this.billState3 = billState3;
    }

    public Double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    public String getPayLink() {
        return payLink;
    }

    public void setPayLink(String payLink) {
        this.payLink = payLink;
    }

    public String getEURTotal() {
        return EURTotal;
    }

    public void setEURTotal(String EURTotal) {
        this.EURTotal = EURTotal;
    }

    public String getEURtoRMB() {
        return EURtoRMB;
    }

    public void setEURtoRMB(String EURtoRMB) {
        this.EURtoRMB = EURtoRMB;
    }

    public String getKpRmb() {
        return kpRmb;
    }

    public void setKpRmb(String kpRmb) {
        this.kpRmb = kpRmb;
    }

    public String getUSDTotalS() {
        return USDTotalS;
    }

    public void setUSDTotalS(String USDTotalS) {
        this.USDTotalS = USDTotalS;
    }

    public String getRemarkOtherIncome() {
        return remarkOtherIncome;
    }

    public void setRemarkOtherIncome(String remarkOtherIncome) {
        this.remarkOtherIncome = remarkOtherIncome;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getTaizangPay() {
        return taizangPay;
    }

    public void setTaizangPay(String taizangPay) {
        this.taizangPay = taizangPay;
    }

    public String getTaizangEditsymbol() {
        return taizangEditsymbol;
    }

    public void setTaizangEditsymbol(String taizangEditsymbol) {
        this.taizangEditsymbol = taizangEditsymbol;
    }

    public String getEUTtoUSD() {
        return EUTtoUSD;
    }

    public void setEUTtoUSD(String EUTtoUSD) {
        this.EUTtoUSD = EUTtoUSD;
    }

    public String getBillLink1() {
        return billLink1;
    }

    public void setBillLink1(String billLink1) {
        this.billLink1 = billLink1;
    }

    public String getTaizangKpEur() {
        return taizangKpEur;
    }

    public void setTaizangKpEur(String taizangKpEur) {
        this.taizangKpEur = taizangKpEur;
    }

    public String getThIncomes() {
        return thIncomes;
    }

    public void setThIncomes(String thIncomes) {
        this.thIncomes = thIncomes;
    }

    public String getBillState1() {
        return billState1;
    }

    public void setBillState1(String billState1) {
        this.billState1 = billState1;
    }

    public String getRemarkFanli() {
        return remarkFanli;
    }

    public void setRemarkFanli(String remarkFanli) {
        this.remarkFanli = remarkFanli;
    }

    public String getTaizangRemark() {
        return taizangRemark;
    }

    public void setTaizangRemark(String taizangRemark) {
        this.taizangRemark = taizangRemark;
    }

    public String getPinupCost() {
        return pinupCost;
    }

    public void setPinupCost(String pinupCost) {
        this.pinupCost = pinupCost;
    }

    public String getPxSettlementVolumet() {
        return pxSettlementVolumet;
    }

    public void setPxSettlementVolumet(String pxSettlementVolumet) {
        this.pxSettlementVolumet = pxSettlementVolumet;
    }

    public String getSiteIncome() {
        return siteIncome;
    }

    public void setSiteIncome(String siteIncome) {
        this.siteIncome = siteIncome;
    }

    public String getClientGrade() {
        return clientGrade;
    }

    public void setClientGrade(String clientGrade) {
        this.clientGrade = clientGrade;
    }

    @Override
    public String toString() {
        return "BusiTaizangInfo{" +
        "id=" + id +
        ", Totalusd=" + Totalusd +
        ", ywFeedback=" + ywFeedback +
        ", costVerify=" + costVerify +
        ", billNum1=" + billNum1 +
        ", billNum2=" + billNum2 +
        ", billNum3=" + billNum3 +
        ", discountsState=" + discountsState +
        ", abnormalCost=" + abnormalCost +
        ", remarkKp=" + remarkKp +
        ", abnormalCostbz=" + abnormalCostbz +
        ", discountsUser=" + discountsUser +
        ", FeeCheckedTime=" + FeeCheckedTime +
        ", guazhangRemark=" + guazhangRemark +
        ", daofuFee=" + daofuFee +
        ", ISRemark=" + ISRemark +
        ", fyEmail=" + fyEmail +
        ", billLink2=" + billLink2 +
        ", shIncomes=" + shIncomes +
        ", discountsTime=" + discountsTime +
        ", taizangFpNo=" + taizangFpNo +
        ", taizangOperatetior=" + taizangOperatetior +
        ", payState=" + payState +
        ", discountsMonth=" + discountsMonth +
        ", USDtoRMB=" + USDtoRMB +
        ", taizangKpRmb=" + taizangKpRmb +
        ", RMBTotalS=" + RMBTotalS +
        ", kpUsd=" + kpUsd +
        ", taizangPayDate=" + taizangPayDate +
        ", USDTotal=" + USDTotal +
        ", chargeuploadDay=" + chargeuploadDay +
        ", taizangOtherIncome=" + taizangOtherIncome +
        ", bgIncomes=" + bgIncomes +
        ", taizangKpUsd=" + taizangKpUsd +
        ", RMBTotal=" + RMBTotal +
        ", billState2=" + billState2 +
        ", confirmationDate=" + confirmationDate +
        ", overweightcost=" + overweightcost +
        ", bxcostfund=" + bxcostfund +
        ", EURTotalS=" + EURTotalS +
        ", taizangId=" + taizangId +
        ", taizangFpDate=" + taizangFpDate +
        ", billNumber=" + billNumber +
        ", paychangeLink=" + paychangeLink +
        ", postDate=" + postDate +
        ", otherIncome=" + otherIncome +
        ", kpEur=" + kpEur +
        ", taizangDpDate=" + taizangDpDate +
        ", taizangExremark=" + taizangExremark +
        ", taizangIsoverdue=" + taizangIsoverdue +
        ", Totalmoney=" + Totalmoney +
        ", paychangeState=" + paychangeState +
        ", pxRoadincome=" + pxRoadincome +
        ", daofuFeecurrency=" + daofuFeecurrency +
        ", huanxiang=" + huanxiang +
        ", issignAgreemen=" + issignAgreemen +
        ", billLink3=" + billLink3 +
        ", TotalmoneyS=" + TotalmoneyS +
        ", taizangOperationtime=" + taizangOperationtime +
        ", billState3=" + billState3 +
        ", discounts=" + discounts +
        ", payLink=" + payLink +
        ", EURTotal=" + EURTotal +
        ", EURtoRMB=" + EURtoRMB +
        ", kpRmb=" + kpRmb +
        ", USDTotalS=" + USDTotalS +
        ", remarkOtherIncome=" + remarkOtherIncome +
        ", billState=" + billState +
        ", taizangPay=" + taizangPay +
        ", taizangEditsymbol=" + taizangEditsymbol +
        ", EUTtoUSD=" + EUTtoUSD +
        ", billLink1=" + billLink1 +
        ", taizangKpEur=" + taizangKpEur +
        ", thIncomes=" + thIncomes +
        ", billState1=" + billState1 +
        ", remarkFanli=" + remarkFanli +
        ", taizangRemark=" + taizangRemark +
        ", pinupCost=" + pinupCost +
        ", pxSettlementVolumet=" + pxSettlementVolumet +
        ", siteIncome=" + siteIncome +
        ", clientGrade=" + clientGrade +
        "}";
    }
}
