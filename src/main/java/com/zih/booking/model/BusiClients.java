package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-21
 */
@TableName("busi_clients")
public class BusiClients implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId("client_ID")
    private String clientId;
    /**
     * 单位名称
     */
    @TableField("client_unit")
    private String clientUnit;
    /**
     * 单位地址
     */
    @TableField("client_address")
    private String clientAddress;
    /**
     * 法人代表
     */
    @TableField("client_legalPerson")
    private String clientLegalperson;
    /**
     * 联系人
     */
    @TableField("client_Contacts")
    private String clientContacts;
    /**
     * 联系电话1
     */
    @TableField("client_tel")
    private String clientTel;
    /**
     * 联系电话2
     */
    @TableField("client_phone")
    private String clientPhone;
    /**
     * 邮件
     */
    @TableField("client_Email")
    private String clientEmail;
    /**
     * 部门
     */
    @TableField("client_dept")
    private String clientDept;
    /**
     * 职位
     */
    @TableField("client_zw")
    private String clientZw;
    /**
     * 推荐人
     */
    @TableField("client_tjr")
    private String clientTjr;
    /**
     * varchar	推荐人ID
     */
    @TableField("client_tjr_id")
    private String clientTjrId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 营业执照
     */
    @TableField("client_license")
    private String clientLicense;
    /**
     * 法人代码证
     */
    @TableField("client_code")
    private String clientCode;
    /**
     * 税务登记证
     */
    @TableField("client_tax")
    private String clientTax;
    /**
     * 登录帐号
     */
    @TableField("client_loginUser")
    private String clientLoginuser;
    /**
     * 登录密码
     */
    @TableField("client_loginPwd")
    private String clientLoginpwd;
    /**
     * 有效开始时间
     */
    @TableField("client_validityDate")
    private Date clientValiditydate;
    /**
     * 到期时间
     */
    @TableField("client_disabledDate")
    private Date clientDisableddate;
    /**
     * 审核人ID
     */
    @TableField("client_examPersonID")
    private String clientExampersonid;
    /**
     * 审核人
     */
    @TableField("client_examPerson")
    private String clientExamperson;
    /**
     * 用户iD
     */
    @TableField("userInfo_ID")
    private String userinfoId;
    /**
     * 用户名称
     */
    @TableField("userInfo_name")
    private String userinfoName;
    /**
     * 审核信息
     */
    private String ExamInfo;
    /**
     * 审核状态（默认0，待审核，1审核通过，2审核不通过
     */
    private String IsExamline;
    /**
     * 账号状态（默认0 正常，1帐户注销,2删除）
     */
    private String cancelAccount;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 创建人ID
     */
    private String createuserid;
    /**
     * 客户子账号id
     */
    @TableField("tempt")
    private String khId;
    //合同时长
    @TableField("contract_time")
    private String contractTime;
    @TableField("contract_status")
    private Integer contractStatus;
    //到期提醒时间，remind_time
    @TableField("remind_time")
    public String remindTime;
    /**
     * 创建人姓名
     */
    private String createusername;
    @TableField("client_jb")
    private String clientJb;
    /**
     * 国内外客户，0是国内，1国外
     */
    @TableField("Eng_Chinese")
    private String engChinese;
    @TableField("en_prename")
    private String enPrename;
    @TableField("en_gender")
    private String enGender;
    @TableField("en_no")
    private String enNo;
    @TableField("en_place")
    private String enPlace;
    @TableField("Register_phone")
    private String registerPhone;
    private String alias;
    private String state;
    /**
     * 是否失信（0否，1是）
     */
    @TableField("is_normal")
    private String isNormal;
    /**
     * 是否直客
     */
    @TableField("is_direct")
    private String isDirect;
    /**
     * 是否签订合同（0是，1否）
     */
    @TableField("is_sign")
    private String isSign;
    /**
     * 西向跟单id
     */
    @TableField("w_Merchandiser_id")
    private String wMerchandiserId;
    /**
     * 西向跟单工号
     */
    @TableField("w_Merchandiser_number")
    private String wMerchandiserNumber;
    /**
     * 西向跟单
     */
    @TableField("w_Merchandiser")
    private String wMerchandiser;
    /**
     * 东向跟单id
     */
    @TableField("e_Merchandiser_id")
    private String eMerchandiserId;
    /**
     * 东向跟单工号
     */
    @TableField("e_Merchandiser_number")
    private String eMerchandiserNumber;
    /**
     * 东向跟单
     */
    @TableField("e_Merchandiser")
    private String eMerchandiser;
    /**
     * 是否为vip
     */
    @TableField("is_vip")
    private String isVip;
    /**
     * 合同有效日期开始
     */
    @TableField("Sign_Date")
    private Date signDate;
    /**
     * 合同失效日期
     */
    @TableField("Sign_disabledDate")
    private Date signDisableddate;
    /**
     * 客户等级
     */
    @TableField("client_grade")
    private String clientGrade;
    /**
     * 客户三个月内转待审核次数平均数
     */
    private String TotalTurnCountAvg;
    /**
     * 老赖标记(0,否，1是)
     */
    @TableField("dead_beat_type")
    private String deadBeatType;
    //权限类型，0所有权限，1只有询价权限
    @TableField("power_type")
    public Integer powerType;



    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }
    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }
    public Integer getPowerType() {
        return powerType;
    }

    public void setPowerType(Integer powerType) {
        this.powerType = powerType;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientUnit() {
        return clientUnit;
    }

    public void setClientUnit(String clientUnit) {
        this.clientUnit = clientUnit;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientLegalperson() {
        return clientLegalperson;
    }

    public void setClientLegalperson(String clientLegalperson) {
        this.clientLegalperson = clientLegalperson;
    }

    public String getClientContacts() {
        return clientContacts;
    }

    public void setClientContacts(String clientContacts) {
        this.clientContacts = clientContacts;
    }

    public String getClientTel() {
        return clientTel;
    }

    public void setClientTel(String clientTel) {
        this.clientTel = clientTel;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientDept() {
        return clientDept;
    }

    public void setClientDept(String clientDept) {
        this.clientDept = clientDept;
    }

    public String getClientZw() {
        return clientZw;
    }

    public void setClientZw(String clientZw) {
        this.clientZw = clientZw;
    }

    public String getClientTjr() {
        return clientTjr;
    }

    public void setClientTjr(String clientTjr) {
        this.clientTjr = clientTjr;
    }

    public String getClientTjrId() {
        return clientTjrId;
    }

    public void setClientTjrId(String clientTjrId) {
        this.clientTjrId = clientTjrId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClientLicense() {
        return clientLicense;
    }

    public void setClientLicense(String clientLicense) {
        this.clientLicense = clientLicense;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientTax() {
        return clientTax;
    }

    public void setClientTax(String clientTax) {
        this.clientTax = clientTax;
    }

    public String getClientLoginuser() {
        return clientLoginuser;
    }

    public void setClientLoginuser(String clientLoginuser) {
        this.clientLoginuser = clientLoginuser;
    }

    public String getClientLoginpwd() {
        return clientLoginpwd;
    }

    public void setClientLoginpwd(String clientLoginpwd) {
        this.clientLoginpwd = clientLoginpwd;
    }

    public Date getClientValiditydate() {
        return clientValiditydate;
    }

    public void setClientValiditydate(Date clientValiditydate) {
        this.clientValiditydate = clientValiditydate;
    }

    public Date getClientDisableddate() {
        return clientDisableddate;
    }

    public void setClientDisableddate(Date clientDisableddate) {
        this.clientDisableddate = clientDisableddate;
    }

    public String getClientExampersonid() {
        return clientExampersonid;
    }

    public void setClientExampersonid(String clientExampersonid) {
        this.clientExampersonid = clientExampersonid;
    }

    public String getClientExamperson() {
        return clientExamperson;
    }

    public void setClientExamperson(String clientExamperson) {
        this.clientExamperson = clientExamperson;
    }

    public String getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(String userinfoId) {
        this.userinfoId = userinfoId;
    }

    public String getUserinfoName() {
        return userinfoName;
    }

    public void setUserinfoName(String userinfoName) {
        this.userinfoName = userinfoName;
    }

    public String getExamInfo() {
        return ExamInfo;
    }

    public void setExamInfo(String ExamInfo) {
        this.ExamInfo = ExamInfo;
    }

    public String getIsExamline() {
        return IsExamline;
    }

    public void setIsExamline(String IsExamline) {
        this.IsExamline = IsExamline;
    }

    public String getCancelAccount() {
        return cancelAccount;
    }

    public void setCancelAccount(String cancelAccount) {
        this.cancelAccount = cancelAccount;
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

    public String getClientJb() {
        return clientJb;
    }

    public void setClientJb(String clientJb) {
        this.clientJb = clientJb;
    }

    public String getEngChinese() {
        return engChinese;
    }

    public void setEngChinese(String engChinese) {
        this.engChinese = engChinese;
    }

    public String getEnPrename() {
        return enPrename;
    }

    public void setEnPrename(String enPrename) {
        this.enPrename = enPrename;
    }

    public String getEnGender() {
        return enGender;
    }

    public void setEnGender(String enGender) {
        this.enGender = enGender;
    }

    public String getEnNo() {
        return enNo;
    }

    public void setEnNo(String enNo) {
        this.enNo = enNo;
    }

    public String getEnPlace() {
        return enPlace;
    }

    public void setEnPlace(String enPlace) {
        this.enPlace = enPlace;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(String isNormal) {
        this.isNormal = isNormal;
    }

    public String getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(String isDirect) {
        this.isDirect = isDirect;
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }

    public String getwMerchandiserId() {
        return wMerchandiserId;
    }

    public void setwMerchandiserId(String wMerchandiserId) {
        this.wMerchandiserId = wMerchandiserId;
    }

    public String getwMerchandiserNumber() {
        return wMerchandiserNumber;
    }

    public void setwMerchandiserNumber(String wMerchandiserNumber) {
        this.wMerchandiserNumber = wMerchandiserNumber;
    }

    public String getwMerchandiser() {
        return wMerchandiser;
    }

    public void setwMerchandiser(String wMerchandiser) {
        this.wMerchandiser = wMerchandiser;
    }

    public String geteMerchandiserId() {
        return eMerchandiserId;
    }

    public void seteMerchandiserId(String eMerchandiserId) {
        this.eMerchandiserId = eMerchandiserId;
    }

    public String geteMerchandiserNumber() {
        return eMerchandiserNumber;
    }

    public void seteMerchandiserNumber(String eMerchandiserNumber) {
        this.eMerchandiserNumber = eMerchandiserNumber;
    }

    public String geteMerchandiser() {
        return eMerchandiser;
    }

    public void seteMerchandiser(String eMerchandiser) {
        this.eMerchandiser = eMerchandiser;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getSignDisableddate() {
        return signDisableddate;
    }

    public void setSignDisableddate(Date signDisableddate) {
        this.signDisableddate = signDisableddate;
    }

    public String getClientGrade() {
        return clientGrade;
    }

    public void setClientGrade(String clientGrade) {
        this.clientGrade = clientGrade;
    }

    public String getTotalTurnCountAvg() {
        return TotalTurnCountAvg;
    }

    public void setTotalTurnCountAvg(String TotalTurnCountAvg) {
        this.TotalTurnCountAvg = TotalTurnCountAvg;
    }

    public String getDeadBeatType() {
        return deadBeatType;
    }

    public void setDeadBeatType(String deadBeatType) {
        this.deadBeatType = deadBeatType;
    }

    public String getKhId() {
        return khId;
    }

    public void setKhId(String khId) {
        this.khId = khId;
    }

    @Override
    public String toString() {
        return "BusiClients{" +
                "clientId=" + clientId +
                ", clientUnit=" + clientUnit +
                ", clientAddress=" + clientAddress +
                ", clientLegalperson=" + clientLegalperson +
                ", clientContacts=" + clientContacts +
                ", clientTel=" + clientTel +
                ", clientPhone=" + clientPhone +
                ", clientEmail=" + clientEmail +
                ", clientDept=" + clientDept +
                ", clientZw=" + clientZw +
                ", clientTjr=" + clientTjr +
                ", clientTjrId=" + clientTjrId +
                ", remark=" + remark +
                ", clientLicense=" + clientLicense +
                ", clientCode=" + clientCode +
                ", clientTax=" + clientTax +
                ", clientLoginuser=" + clientLoginuser +
                ", clientLoginpwd=" + clientLoginpwd +
                ", clientValiditydate=" + clientValiditydate +
                ", clientDisableddate=" + clientDisableddate +
                ", clientExampersonid=" + clientExampersonid +
                ", clientExamperson=" + clientExamperson +
                ", userinfoId=" + userinfoId +
                ", userinfoName=" + userinfoName +
                ", ExamInfo=" + ExamInfo +
                ", IsExamline=" + IsExamline +
                ", cancelAccount=" + cancelAccount +
                ", createdate=" + createdate +
                ", createuserid=" + createuserid +
                ", createusername=" + createusername +
                ", clientJb=" + clientJb +
                ", engChinese=" + engChinese +
                ", enPrename=" + enPrename +
                ", enGender=" + enGender +
                ", enNo=" + enNo +
                ", enPlace=" + enPlace +
                ", registerPhone=" + registerPhone +
                ", alias=" + alias +
                ", state=" + state +
                ", isNormal=" + isNormal +
                ", isDirect=" + isDirect +
                ", isSign=" + isSign +
                ", wMerchandiserId=" + wMerchandiserId +
                ", wMerchandiserNumber=" + wMerchandiserNumber +
                ", wMerchandiser=" + wMerchandiser +
                ", eMerchandiserId=" + eMerchandiserId +
                ", eMerchandiserNumber=" + eMerchandiserNumber +
                ", eMerchandiser=" + eMerchandiser +
                ", isVip=" + isVip +
                ", signDate=" + signDate +
                ", signDisableddate=" + signDisableddate +
                ", clientGrade=" + clientGrade +
                ", TotalTurnCountAvg=" + TotalTurnCountAvg +
                ", deadBeatType=" + deadBeatType +
                ", khid=" + khId +
                ", powerType=" + powerType +
                "}";
    }
}
