package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zih.booking.system.annotation.PropertyMsg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wsl123
 * @since 2020-04-27
 */
@TableName("busi_shippingorder")
public class BusiShippingorder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id,主键
     */
    @PropertyMsg("订单id")
    @TableId(value = "order_ID",type = IdType.UUID)
    private String orderId;
    /**
     * 站点ID,回程入货通知书上堆场地址
     */
    @PropertyMsg("站点ID")
    private String stationID;
    /**
     * 订单号（委托书编号）（舱位号）
     */
    @PropertyMsg("舱位号")
    @TableField("order_number")
    private String orderNumber;
    /**
     * 服务：0门到门 1门到站 2站到站 3站到门
     */
    @TableField("booking_service")
    private String bookingService;
    /**
     * 集装箱箱属 :0委托ZIH 1自备 2自备铁路箱3 自备非铁路箱
     */
    @PropertyMsg("集装箱箱属")
    @TableField("order_audit_belongTo")
    private String orderAuditBelongto;
    /**
     * 创建时间
     */
    @TableField("order_audit_createdate")
    private Date orderAuditCreatedate;
    /**
     * 创建用户ID
     */
    @TableField("order_audit_userid")
    private String orderAuditUserid;
    /**
     * 创建用户姓名
     */
    @PropertyMsg("创建用户姓名")
    @TableField("order_audit_username")
    private String orderAuditUsername;
    /**
     * 班列ID
     */
    @PropertyMsg("班列ID")
    @TableField("class_ID")
    private String classId;
    /**
     * 班列日期
     */
    @PropertyMsg("班列日期")
    @TableField("Class_date")
    private Date classDate;
    /**
     * 班列站点
     */
    @PropertyMsg("班列站点")
    @TableField("Class_site")
    private String classSite;
    /**
     * 班列编号
     */
    @PropertyMsg("班列编号")
    @TableField("order_class_bh")
    private String orderClassBh;
    /**
     * 线路类型：0中欧 2中亚 3中越 4中俄
     */
    @PropertyMsg("线路类型")
    @TableField("line_typeid")
    private String lineTypeid;
    /**
     * 班列号
     */
    @PropertyMsg("班列号")
    @TableField("class_number")
    private String classNumber;
    /**
     * 0为去程 1为回程
     */
    @PropertyMsg("去回程")
    @TableField("class_EastAndWest")
    private String classEastandwest;
    /**
     * 上货站
     */
    @PropertyMsg("上货站")
    @TableField("order_uploadSite")
    private String orderUploadsite;
    /**
     * 下货站
     */
    @PropertyMsg("下货站")
    @TableField("order_unloadSite")
    private String orderUnloadsite;
    /**
     * 上货站唯一码
     */
    @PropertyMsg("订单id")
    @TableField("order_uploadCode")
    private String orderUploadcode;
    /**
     * 下货丫唯一码
     */
    @TableField("order_unloadCode")
    private String orderUnloadcode;
    /**
     * 贸易方式（条款ID）
     */
    @TableField("dict_ID")
    private Integer dictId;
    /**
     * 条款名称
     */
    @PropertyMsg("条款名称")
    @TableField("dict_name")
    private String dictName;
    /**
     * 部门
     */
    @PropertyMsg("部门")
    @TableField("order_deptName")
    private String orderDeptname;
    /**
     * 跟单姓名
     */
    @PropertyMsg("跟单姓名")
    @TableField("order_merchandiser")
    private String orderMerchandiser;
    /**
     * 跟单ID
     */
    @TableField("order_merchandiser_id")
    private String orderMerchandiserId;
    /**
     * 是否需要发票 0否 1是
     */
    @TableField("order_isTicket")
    private String orderIsticket;
    /**
     * 订舱方ID/客户id
     */
    @PropertyMsg("订舱方ID")
    @TableField("client_ID")
    private String clientId;

    @TableField("kh_id")
    private Long khId; //客户子账号id
    /**
     * 订舱方名称
     */
    @PropertyMsg("订舱方名称")
    @TableField("client_unit")
    private String clientUnit;
    /**
     * 订舱方联系人
     */
    @PropertyMsg("订舱方联系人")
    @TableField("client_contacts")
    private String clientContacts;
    /**
     * 订舱方联系方式
     */
    @PropertyMsg("订舱方联系方式")
    @TableField("client_tel")
    private String clientTel;
    /**
     * 订舱方邮箱
     */
    @PropertyMsg("订舱方邮箱")
    @TableField("client_email")
    private String clientEmail;
    /**
     * 订舱方地址
     */
    @PropertyMsg("订舱方地址")
    @TableField("client_address")
    private String clientAddress;
    /**
     * 报关地点
     */
    @PropertyMsg("报关地点")
    @TableField("client_order_bindingAddress")
    private String clientOrderBindingaddress;
    /**
     * zih报关：0是 1否
     */
    @TableField("client_order_bindingWay")
    private String clientOrderBindingway;
    /**
     * 特殊要求备注
     */
    @PropertyMsg("特殊要求备注")
    @TableField("client_order_remarks")
    private String clientOrderRemarks;
    /**
     * 集装箱箱量
     */
    @PropertyMsg("集装箱箱量")
    @TableField("container_boxAmount")
    private String containerBoxamount;
    /**
     * 集装箱箱型
     */
    @PropertyMsg("集装箱箱型")
    @TableField("container_type")
    private String containerType;
    /**
     * 箱型值
     */
    @PropertyMsg("箱型值")
    @TableField("container_typeval")
    private String containerTypeval;
    /**
     * 还箱点
     */
    @PropertyMsg("还箱点")
    @TableField("client_order_repayContainer")
    private String clientOrderRepaycontainer;
    /**
     * 是否派监装员 0否 1是
     */
    @TableField("ship_order_isDispatch")
    private String shipOrderIsdispatch;
    /**
     * 发货方id
     */
    @TableField("ship_order_id")
    private String shipOrderId;
    /**
     * 发货方名称
     */
    @PropertyMsg("发货方名称")
    @TableField("ship_oreder_name")
    private String shipOrederName;
    /**
     * 发货方联系人
     */
    @PropertyMsg("发货方联系人")
    @TableField("ship_oreder_contacts")
    private String shipOrederContacts;
    /**
     * 发货方邮箱
     */
    @PropertyMsg("发货方邮箱")
    @TableField("ship_oreder_email")
    private String shipOrederEmail;
    /**
     * 发货方联系电话
     */
    @PropertyMsg("发货方联系电话")
    @TableField("ship_oreder_phone")
    private String shipOrederPhone;
    /**
     * 发货方地址
     */
    @PropertyMsg("发货方地址")
    @TableField("ship_oreder_address")
    private String shipOrederAddress;
    /**
     * 委托ZIH提货 0是 1否  2铁路到货
     */
    @TableField("ship_order_binningWay")
    private String shipOrderBinningway;
    /**
     * 提货地址
     */
    @PropertyMsg("提货地址")
    @TableField("ship_order_unloadAddress")
    private String shipOrderUnloadaddress;
    /**
     * 提货联系人
     */
    @PropertyMsg("提货联系人")
    @TableField("ship_order_unloadContacts")
    private String shipOrderUnloadcontacts;
    /**
     * 提货联系方式
     */
    @PropertyMsg("提货联系方式")
    @TableField("ship_order_unloadWay")
    private String shipOrderUnloadway;
    /**
     * 提货联系邮箱
     */
    @PropertyMsg("提货联系邮箱")
    @TableField("ship_order_unloadWay_email")
    private String shipOrderUnloadwayEmail;
    /**
     * 提货时间
     */
    @PropertyMsg("提货时间")
    @TableField("ship_order_unloadTime")
    private Date shipOrderUnloadtime;
    /**
     * 自送货时间
     */
    @PropertyMsg("自送货时间")
    @TableField("ship_order_sendTime")
    private Date shipOrderSendtime;
    /**
     * 收货方id
     */
    @TableField("receive_order_id")
    private String receiveOrderId;
    /**
     * 收货方名称
     */
    @PropertyMsg("收货方名称")
    @TableField("receive_order_name")
    private String receiveOrderName;
    /**
     * 收货方联系人
     */
    @PropertyMsg("收货方联系人")
    @TableField("receive_order_contacts")
    private String receiveOrderContacts;
    /**
     * 到站通知提货邮箱
     */
    @PropertyMsg("到站通知提货邮箱")
    @TableField("receive_order_mail")
    private String receiveOrderMail;
    /**
     * 收货方联系电话
     */
    @PropertyMsg("收货方联系电话")
    @TableField("receive_order_phone")
    private String receiveOrderPhone;
    /**
     * 通讯地址
     */
    @PropertyMsg("通讯地址")
    @TableField("receive_order_address")
    private String receiveOrderAddress;
    /**
     * 是否由ZIH代理清关  0否 1是
     */
    @TableField("receive_order_isClearance")
    private String receiveOrderIsclearance;
    /**
     * 是否由ZIH代理送货  0否 1是
     */
    @TableField("receive_order_isPart")
    private String receiveOrderIspart;

    /** 分拨方式（0整柜派送，1拆箱散货派送) */
    @TableField("distribution_type")
    private String distributionType;

    /**
     * 送货分拨邮箱
     */
    @PropertyMsg("送货分拨邮箱")
    @TableField("receive_order_zihemail")
    private String receiveOrderZihemail;
    /**
     * 分拨状态0待分拨，1已分拨
     */
    @TableField("receive_order_fb_status")
    private String receiveOrderFbStatus;
    /**
     * 送货地址
     */
    @PropertyMsg("送货地址")
    @TableField("receive_order_PartAddress")
    private String receiveOrderPartaddress;
    /**
     * 在途信息接收邮箱
     */
    @PropertyMsg("在途信息接收邮箱")
    @TableField("receive_order_receiveEmail")
    private String receiveOrderReceiveemail;
    /**
     * 收货方英文
     */
    @TableField("receive_order_en_name")
    private String receiveOrderEnName;
    /**
     * 收货方联系人英文
     */
    @TableField("receive_order_en_contacts")
    private String receiveOrderEnContacts;
    /**
     * 收货方邮箱英文
     */
    @TableField("receive_order_en_email")
    private String receiveOrderEnEmail;
    /**
     * 收货方电话英文
     */
    @TableField("receive_order_en_phone")
    private String receiveOrderEnPhone;
    /**
     * 收货方通讯地址英文
     */
    @TableField("receive_order_en_address")
    private String receiveOrderEnAddress;
    /**
     * 收货方俄文
     */
    @TableField("receive_order_Ename")
    private String receiveOrderEname;
    /**
     * 收货方联系人俄文
     */
    @TableField("receive_order_Econtacts")
    private String receiveOrderEcontacts;
    /**
     * 收货方邮箱俄文
     */
    @TableField("receive_order_Eemail")
    private String receiveOrderEemail;
    /**
     * 收货方电话俄文
     */
    @TableField("receive_order_Ephone")
    private String receiveOrderEphone;
    /**
     * 收货方通讯地址俄文
     */
    @PropertyMsg("订单id")
    @TableField("receive_order_Eaddress")
    private String receiveOrderEaddress;
    /**
     * 到站提箱公司名称（俄线）
     */
    @TableField("Etx_Company")
    private String etxCompany;
    /**
     * 到货提箱公司税号（俄线）
     */
    private String Eduty;
    /**
     * 承担监管区费用的公司（或个人）名称(俄线)
     */
    @TableField("Etx_name")
    private String etxName;
    /**
     * 送货分拨联系人
     */
    @TableField("receive_order_zihcontacts")
    private String receiveOrderZihcontacts;
    /**
     * 送货分拨联系电话
     */
    @TableField("receive_order_zihtel")
    private String receiveOrderZihtel;
    /**
     * 是否可堆叠（1是0否2仅可自叠）
     */
    @TableField("goods_cannotPileUp")
    private String goodsCannotpileup;
    /**
     * 是否易碎（1是0否）
     */
    @TableField("goods_fragile")
    private String goodsFragile;
    /**
     * 单件超长超重（1是0否）
     */
    @TableField("goods_general")
    private String goodsGeneral;
    /**
     * 0未审核 1已审核通过
     2已审核未通过 3已取消的委托
     4转待审核  5草稿
     */
    private String IsExamline;
    /**
     *  整拼箱 0整柜 1拼箱
     */
    private String IsConsolidation;
    /**
     * 托书操作创建时间
     */
    private Date createdate;
    /**
     * 托书操作创建人ID
     */
    private String createuserid;
    /**
     * 托书操作创建人名称
     */
    @PropertyMsg("托书操作创建人名称")
    private String createusername;
    /**
     * 提货费用
     */
    @TableField("ship_th_cost")
    private String shipThCost;
    /**
     * 报关费用
     */
    @TableField("client_bg_cost")
    private String clientBgCost;
    /**
     * 收货还箱地
     */
    @PropertyMsg("订单id")
    @TableField("receive_hx_address")
    private String receiveHxAddress;
    /**
     * 箱管费
     */
    @TableField("receive_xg_cost")
    private String receiveXgCost;
    /**
     * 清关费用
     */
    @TableField("receive_qg_cost")
    private String receiveQgCost;
    /**
     * 监装费用
     */
    @TableField("ship_jz_cost")
    private String shipJzCost;
    /**
     * 送货费用
     */
    @TableField("receive_sh_cost")
    private String receiveShCost;
    /**
     * 站到站运费
     */
    private String siteCost;
    /**
     * 客户推荐人
     */
    @PropertyMsg("客户推荐人")
    @TableField("client_tjr")
    private String clientTjr;
    /**
     * 客户推荐人id
     */
    @TableField("client_tjr_id")
    private String clientTjrId;
    /**
     * 回程发货提箱地
     */
    @PropertyMsg("回程发货提箱地")
    @TableField("ship_fh_site")
    private String shipFhSite;
    /**
     * 去程发货提箱地
     */
    @PropertyMsg("去程发货提箱地")
    @TableField("ship_hyd")
    private String shipHyd;
    /**
     * 到货城市，就近还空箱
     */
    @PropertyMsg("到货城市，就近还空箱")
    @TableField("receive_hyd")
    private String receiveHyd;
    /**
     * 提货方式（整箱到车站，散货到堆场）针对整柜
     */
    @PropertyMsg("提货方式")
    @TableField("ship_th_type")
    private String shipThType;
    /**
     * 委托ZIH提货（0是整箱到车站，1是散货到堆场）针对整柜
     */
    @TableField("ship_th_type_id")
    private String shipThTypeId;
    /**
     * 发货方自送货方式 0散货到堆场 1整箱到车站
     */
    @PropertyMsg("发货方自送货方式")
    @TableField("ship_zs_type")
    private String shipZsType;
    /**
     * 发货方自送货ID
     */
    @TableField("ship_zs_type_id")
    private String shipZsTypeId;
    /**
     * 语言（0是中文订单1是英文）
     */
    private String yuyan;
    /**
     * 客户每次托书提交时间
     */
    @TableField("tj_time")
    private Date tjTime;
    /**
     * 客户首次提交托书时间
     */
    @TableField("tj_f_time")
    private Date tjFTime;
    /**
     * 重箱托书编号
     */
    @PropertyMsg("重箱托书编号")
    @TableField("zx_number")
    private String zxNumber;
    /**
     * 1有修改记录0没有修改记录
     */
    private String isupdate;
    /**
     * 0手机下单
     */
    private String IsPhone;
    /**
     * 提货费用币种（0是美金1是人民币）
     */
    @TableField("zx_THCost_Currency")
    private String zxThcostCurrency;
    /**
     * 提货费报价编号
     */
    @TableField("ship_th_cost_NO")
    private String shipThCostNo;
    /**
     * 业务员编码
     */
    @TableField("YW_Number")
    private String ywNumber;

    //业务编号
    @TableField("client_yw_number")
    private String clientYwNumber;
    /**
     * 是否上车（运综货物状态）
     */
    private String IsGetin;
    /**
     * 每日转待审核次数（每日两次）
     */
    private Integer TurnCount;
    /**
     * 3个月内客户转待审核次数和
     */
    private Integer TotalTurnCount;
    /**
     * 3个月内客户转待审核次数平均数
     */
    private BigDecimal TotalTurnCountAvg;
    /**
     * 实际班列日期
     */
    @PropertyMsg("实际班列日期")
    @TableField("Actual_classDate")
    private String actualClassdate;
    /**
     * 业务审核费用状态：1审核成功，默认0
     */
    @TableField("YW_Feedback")
    private Integer ywFeedback;
    /**
     * 去程提货地，回程送货地详细地址（去回程）
     */
    @PropertyMsg("去程提货地，回程送货地详细地址")
    @TableField("detailed_address")
    private String detailedAddress;
    /**
     * 是否可提前班列（0是1否）
     */
    @TableField("putoff_class")
    private Integer putoffClass;
    /**
     * 费用确认单确认状态0未确认，1已确认
     */
    @TableField("Cost_verify")
    private String costVerify;
    /**
     * 是否有改单 0否 1是
     */
    @TableField("DC_gaidan_state")
    private String dcGaidanState;
    /**
     * 送货报价编号
     */
    @TableField("receive_sh_cost_id")
    private String receiveShCostId;
    /**
     * 收货方邮箱
     */
    @TableField("receive_order_Email")
    private String receiveOrderEmail;
    /**
     * 箱管费币种
     */
    @TableField("xg_costCurrency")
    private String xgCostcurrency;
    /**
     * 清关费币种
     */
    @TableField("qg_costCurrency")
    private String qgCostcurrency;
    /**
     * 送货费币种
     */
    @TableField("sh_costCurrency")
    private String shCostcurrency;
    /**
     * 铁路运费币种
     */
    @TableField("siteCost_Currency")
    private String sitecostCurrency;
    /**
     * 审核操作时间
     */
    @TableField("Exame_time")
    private Date exameTime;
    /**
     * 跟单船级社证书上传状态
     */
    @TableField("gd_boat")
    private String gdBoat;
    /**
     * 跟单船级社证书url
     */
    @TableField("gd_boaturl")
    private String gdBoaturl;
    /**
     * 跟单船级社证书备注
     */
    @TableField("GD_cjsRemark")
    private String gdCjsremark;
    /**
     * 跟单部押金保函URL
     */
    @TableField("gd_voucherUrl")
    private String gdVoucherurl;
    /**
     * 跟单押金保函上传状态0是已上传
     */
    @TableField("gd_voucher")
    private String gdVoucher;
    /**
     * 运综状态
     */
    @TableField("train_state")
    private String trainState;
    /**
     * 询价结果id
     */
    @TableField("inquiry_record_id")
    private String inquiryRecordId;
    @TableField("pick_up_box_fee")
    private String pickUpBoxFee;
    @TableField("return_box_fee")
    private String returnBoxFee;

    @TableField("limitation")
    private String limitation;
    @TableField("truck_type")
    private String truckType;
    @TableField("ConsignorC")
    private String consignorc;

    @TableField("EConsignorState")
    private String econsignorstate;

    @TableField("domestic_number")
    private String domesticNumber;

    @TableField("foreign_number")
    private String foreignNumber;

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }
    public String getLimitation() {
        return limitation;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }
    public String getTruckType() {
        return truckType;
    }

    public void setConsignorc(String consignorc) {
        this.consignorc = consignorc;
    }
    public String getConsignorc() {
        return consignorc;
    }

    public String getEconsignorstate() {
        return econsignorstate;
    }
    public void setEconsignorstate(String econsignorstate) {
        this.econsignorstate = econsignorstate;
    }

    public String getDomesticNumber() {
        return domesticNumber;
    }
    public void setDomesticNumber(String domesticNumber) {
        this.domesticNumber = domesticNumber;
    }

    public String getForeignNumber() {
        return foreignNumber;
    }
    public void setForeignNumber(String foreignNumber) {
        this.foreignNumber = foreignNumber;
    }

    public String getPickUpBoxFee() {
        return pickUpBoxFee;
    }
    public void setPickUpBoxFee(String pickUpBoxFee) {
        this.pickUpBoxFee = pickUpBoxFee;
    }

    public String getReturnBoxFee() {
        return returnBoxFee;
    }

    public void setReturnBoxFee(String returnBoxFee) {
        this.returnBoxFee = returnBoxFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBookingService() {
        return bookingService;
    }

    public void setBookingService(String bookingService) {
        this.bookingService = bookingService;
    }

    public String getOrderAuditBelongto() {
        return orderAuditBelongto;
    }

    public void setOrderAuditBelongto(String orderAuditBelongto) {
        this.orderAuditBelongto = orderAuditBelongto;
    }

    public Date getOrderAuditCreatedate() {
        return orderAuditCreatedate;
    }

    public void setOrderAuditCreatedate(Date orderAuditCreatedate) {
        this.orderAuditCreatedate = orderAuditCreatedate;
    }

    public String getOrderAuditUserid() {
        return orderAuditUserid;
    }

    public void setOrderAuditUserid(String orderAuditUserid) {
        this.orderAuditUserid = orderAuditUserid;
    }

    public String getOrderAuditUsername() {
        return orderAuditUsername;
    }

    public void setOrderAuditUsername(String orderAuditUsername) {
        this.orderAuditUsername = orderAuditUsername;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public String getClassSite() {
        return classSite;
    }

    public void setClassSite(String classSite) {
        this.classSite = classSite;
    }

    public String getOrderClassBh() {
        return orderClassBh;
    }

    public void setOrderClassBh(String orderClassBh) {
        this.orderClassBh = orderClassBh;
    }

    public String getLineTypeid() {
        return lineTypeid;
    }

    public void setLineTypeid(String lineTypeid) {
        this.lineTypeid = lineTypeid;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassEastandwest() {
        return classEastandwest;
    }

    public void setClassEastandwest(String classEastandwest) {
        this.classEastandwest = classEastandwest;
    }

    public String getOrderUploadsite() {
        return orderUploadsite;
    }

    public void setOrderUploadsite(String orderUploadsite) {
        this.orderUploadsite = orderUploadsite;
    }

    public String getOrderUnloadsite() {
        return orderUnloadsite;
    }

    public void setOrderUnloadsite(String orderUnloadsite) {
        this.orderUnloadsite = orderUnloadsite;
    }

    public String getOrderUploadcode() {
        return orderUploadcode;
    }

    public void setOrderUploadcode(String orderUploadcode) {
        this.orderUploadcode = orderUploadcode;
    }

    public String getOrderUnloadcode() {
        return orderUnloadcode;
    }

    public void setOrderUnloadcode(String orderUnloadcode) {
        this.orderUnloadcode = orderUnloadcode;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getOrderDeptname() {
        return orderDeptname;
    }

    public void setOrderDeptname(String orderDeptname) {
        this.orderDeptname = orderDeptname;
    }

    public String getOrderMerchandiser() {
        return orderMerchandiser;
    }

    public void setOrderMerchandiser(String orderMerchandiser) {
        this.orderMerchandiser = orderMerchandiser;
    }

    public String getOrderMerchandiserId() {
        return orderMerchandiserId;
    }

    public void setOrderMerchandiserId(String orderMerchandiserId) {
        this.orderMerchandiserId = orderMerchandiserId;
    }

    public String getOrderIsticket() {
        return orderIsticket;
    }

    public void setOrderIsticket(String orderIsticket) {
        this.orderIsticket = orderIsticket;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getKhId() {
        return khId;
    }

    public void setKhId(Long khId) {
        this.khId = khId;
    }

    public String getClientUnit() {
        return clientUnit;
    }

    public void setClientUnit(String clientUnit) {
        this.clientUnit = clientUnit;
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

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientOrderBindingaddress() {
        return clientOrderBindingaddress;
    }

    public void setClientOrderBindingaddress(String clientOrderBindingaddress) {
        this.clientOrderBindingaddress = clientOrderBindingaddress;
    }

    public String getClientOrderBindingway() {
        return clientOrderBindingway;
    }

    public void setClientOrderBindingway(String clientOrderBindingway) {
        this.clientOrderBindingway = clientOrderBindingway;
    }

    public String getClientOrderRemarks() {
        return clientOrderRemarks;
    }

    public void setClientOrderRemarks(String clientOrderRemarks) {
        this.clientOrderRemarks = clientOrderRemarks;
    }

    public String getContainerBoxamount() {
        return containerBoxamount;
    }

    public void setContainerBoxamount(String containerBoxamount) {
        this.containerBoxamount = containerBoxamount;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerTypeval() {
        return containerTypeval;
    }

    public void setContainerTypeval(String containerTypeval) {
        this.containerTypeval = containerTypeval;
    }

    public String getClientOrderRepaycontainer() {
        return clientOrderRepaycontainer;
    }

    public void setClientOrderRepaycontainer(String clientOrderRepaycontainer) {
        this.clientOrderRepaycontainer = clientOrderRepaycontainer;
    }

    public String getShipOrderIsdispatch() {
        return shipOrderIsdispatch;
    }

    public void setShipOrderIsdispatch(String shipOrderIsdispatch) {
        this.shipOrderIsdispatch = shipOrderIsdispatch;
    }

    public String getShipOrderId() {
        return shipOrderId;
    }

    public void setShipOrderId(String shipOrderId) {
        this.shipOrderId = shipOrderId;
    }

    public String getShipOrederName() {
        return shipOrederName;
    }

    public void setShipOrederName(String shipOrederName) {
        this.shipOrederName = shipOrederName;
    }

    public String getShipOrederContacts() {
        return shipOrederContacts;
    }

    public void setShipOrederContacts(String shipOrederContacts) {
        this.shipOrederContacts = shipOrederContacts;
    }

    public String getShipOrederEmail() {
        return shipOrederEmail;
    }

    public void setShipOrederEmail(String shipOrederEmail) {
        this.shipOrederEmail = shipOrederEmail;
    }

    public String getShipOrederPhone() {
        return shipOrederPhone;
    }

    public void setShipOrederPhone(String shipOrederPhone) {
        this.shipOrederPhone = shipOrederPhone;
    }

    public String getShipOrederAddress() {
        return shipOrederAddress;
    }

    public void setShipOrederAddress(String shipOrederAddress) {
        this.shipOrederAddress = shipOrederAddress;
    }

    public String getShipOrderBinningway() {
        return shipOrderBinningway;
    }

    public void setShipOrderBinningway(String shipOrderBinningway) {
        this.shipOrderBinningway = shipOrderBinningway;
    }

    public String getShipOrderUnloadaddress() {
        return shipOrderUnloadaddress;
    }

    public void setShipOrderUnloadaddress(String shipOrderUnloadaddress) {
        this.shipOrderUnloadaddress = shipOrderUnloadaddress;
    }

    public String getShipOrderUnloadcontacts() {
        return shipOrderUnloadcontacts;
    }

    public void setShipOrderUnloadcontacts(String shipOrderUnloadcontacts) {
        this.shipOrderUnloadcontacts = shipOrderUnloadcontacts;
    }

    public String getShipOrderUnloadway() {
        return shipOrderUnloadway;
    }

    public void setShipOrderUnloadway(String shipOrderUnloadway) {
        this.shipOrderUnloadway = shipOrderUnloadway;
    }

    public String getShipOrderUnloadwayEmail() {
        return shipOrderUnloadwayEmail;
    }

    public void setShipOrderUnloadwayEmail(String shipOrderUnloadwayEmail) {
        this.shipOrderUnloadwayEmail = shipOrderUnloadwayEmail;
    }

    public Date getShipOrderUnloadtime() {
        return shipOrderUnloadtime;
    }

    public void setShipOrderUnloadtime(Date shipOrderUnloadtime) {
        this.shipOrderUnloadtime = shipOrderUnloadtime;
    }

    public Date getShipOrderSendtime() {
        return shipOrderSendtime;
    }

    public void setShipOrderSendtime(Date shipOrderSendtime) {
        this.shipOrderSendtime = shipOrderSendtime;
    }

    public String getReceiveOrderId() {
        return receiveOrderId;
    }

    public void setReceiveOrderId(String receiveOrderId) {
        this.receiveOrderId = receiveOrderId;
    }

    public String getReceiveOrderName() {
        return receiveOrderName;
    }

    public void setReceiveOrderName(String receiveOrderName) {
        this.receiveOrderName = receiveOrderName;
    }

    public String getReceiveOrderContacts() {
        return receiveOrderContacts;
    }

    public void setReceiveOrderContacts(String receiveOrderContacts) {
        this.receiveOrderContacts = receiveOrderContacts;
    }

    public String getReceiveOrderMail() {
        return receiveOrderMail;
    }

    public void setReceiveOrderMail(String receiveOrderMail) {
        this.receiveOrderMail = receiveOrderMail;
    }

    public String getReceiveOrderPhone() {
        return receiveOrderPhone;
    }

    public void setReceiveOrderPhone(String receiveOrderPhone) {
        this.receiveOrderPhone = receiveOrderPhone;
    }

    public String getReceiveOrderAddress() {
        return receiveOrderAddress;
    }

    public void setReceiveOrderAddress(String receiveOrderAddress) {
        this.receiveOrderAddress = receiveOrderAddress;
    }

    public String getReceiveOrderIsclearance() {
        return receiveOrderIsclearance;
    }

    public void setReceiveOrderIsclearance(String receiveOrderIsclearance) {
        this.receiveOrderIsclearance = receiveOrderIsclearance;
    }

    public String getReceiveOrderIspart() {
        return receiveOrderIspart;
    }

    public void setReceiveOrderIspart(String receiveOrderIspart) {
        this.receiveOrderIspart = receiveOrderIspart;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }
    public String getDistributionType() {
        return distributionType;
    }

    public String getReceiveOrderZihemail() {
        return receiveOrderZihemail;
    }

    public void setReceiveOrderZihemail(String receiveOrderZihemail) {
        this.receiveOrderZihemail = receiveOrderZihemail;
    }

    public String getReceiveOrderFbStatus() {
        return receiveOrderFbStatus;
    }

    public void setReceiveOrderFbStatus(String receiveOrderFbStatus) {
        this.receiveOrderFbStatus = receiveOrderFbStatus;
    }

    public String getReceiveOrderPartaddress() {
        return receiveOrderPartaddress;
    }

    public void setReceiveOrderPartaddress(String receiveOrderPartaddress) {
        this.receiveOrderPartaddress = receiveOrderPartaddress;
    }

    public String getReceiveOrderReceiveemail() {
        return receiveOrderReceiveemail;
    }

    public void setReceiveOrderReceiveemail(String receiveOrderReceiveemail) {
        this.receiveOrderReceiveemail = receiveOrderReceiveemail;
    }

    public String getReceiveOrderEnName() {
        return receiveOrderEnName;
    }

    public void setReceiveOrderEnName(String receiveOrderEnName) {
        this.receiveOrderEnName = receiveOrderEnName;
    }

    public String getReceiveOrderEnContacts() {
        return receiveOrderEnContacts;
    }

    public void setReceiveOrderEnContacts(String receiveOrderEnContacts) {
        this.receiveOrderEnContacts = receiveOrderEnContacts;
    }

    public String getReceiveOrderEnEmail() {
        return receiveOrderEnEmail;
    }

    public void setReceiveOrderEnEmail(String receiveOrderEnEmail) {
        this.receiveOrderEnEmail = receiveOrderEnEmail;
    }

    public String getReceiveOrderEnPhone() {
        return receiveOrderEnPhone;
    }

    public void setReceiveOrderEnPhone(String receiveOrderEnPhone) {
        this.receiveOrderEnPhone = receiveOrderEnPhone;
    }

    public String getReceiveOrderEnAddress() {
        return receiveOrderEnAddress;
    }

    public void setReceiveOrderEnAddress(String receiveOrderEnAddress) {
        this.receiveOrderEnAddress = receiveOrderEnAddress;
    }

    public String getReceiveOrderEname() {
        return receiveOrderEname;
    }

    public void setReceiveOrderEname(String receiveOrderEname) {
        this.receiveOrderEname = receiveOrderEname;
    }

    public String getReceiveOrderEcontacts() {
        return receiveOrderEcontacts;
    }

    public void setReceiveOrderEcontacts(String receiveOrderEcontacts) {
        this.receiveOrderEcontacts = receiveOrderEcontacts;
    }

    public String getReceiveOrderEemail() {
        return receiveOrderEemail;
    }

    public void setReceiveOrderEemail(String receiveOrderEemail) {
        this.receiveOrderEemail = receiveOrderEemail;
    }

    public String getReceiveOrderEphone() {
        return receiveOrderEphone;
    }

    public void setReceiveOrderEphone(String receiveOrderEphone) {
        this.receiveOrderEphone = receiveOrderEphone;
    }

    public String getReceiveOrderEaddress() {
        return receiveOrderEaddress;
    }

    public void setReceiveOrderEaddress(String receiveOrderEaddress) {
        this.receiveOrderEaddress = receiveOrderEaddress;
    }

    public String getEtxCompany() {
        return etxCompany;
    }

    public void setEtxCompany(String etxCompany) {
        this.etxCompany = etxCompany;
    }

    public String getEduty() {
        return Eduty;
    }

    public void setEduty(String Eduty) {
        this.Eduty = Eduty;
    }

    public String getEtxName() {
        return etxName;
    }

    public void setEtxName(String etxName) {
        this.etxName = etxName;
    }

    public String getReceiveOrderZihcontacts() {
        return receiveOrderZihcontacts;
    }

    public void setReceiveOrderZihcontacts(String receiveOrderZihcontacts) {
        this.receiveOrderZihcontacts = receiveOrderZihcontacts;
    }

    public String getReceiveOrderZihtel() {
        return receiveOrderZihtel;
    }

    public void setReceiveOrderZihtel(String receiveOrderZihtel) {
        this.receiveOrderZihtel = receiveOrderZihtel;
    }

    public String getGoodsCannotpileup() {
        return goodsCannotpileup;
    }

    public void setGoodsCannotpileup(String goodsCannotpileup) {
        this.goodsCannotpileup = goodsCannotpileup;
    }

    public String getGoodsFragile() {
        return goodsFragile;
    }

    public void setGoodsFragile(String goodsFragile) {
        this.goodsFragile = goodsFragile;
    }

    public String getGoodsGeneral() {
        return goodsGeneral;
    }

    public void setGoodsGeneral(String goodsGeneral) {
        this.goodsGeneral = goodsGeneral;
    }

    public String getIsExamline() {
        return IsExamline;
    }

    public void setIsExamline(String IsExamline) {
        this.IsExamline = IsExamline;
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

    public String getShipThCost() {
        return shipThCost;
    }

    public void setShipThCost(String shipThCost) {
        this.shipThCost = shipThCost;
    }

    public String getClientBgCost() {
        return clientBgCost;
    }

    public void setClientBgCost(String clientBgCost) {
        this.clientBgCost = clientBgCost;
    }

    public String getReceiveHxAddress() {
        return receiveHxAddress;
    }

    public void setReceiveHxAddress(String receiveHxAddress) {
        this.receiveHxAddress = receiveHxAddress;
    }

    public String getReceiveXgCost() {
        return receiveXgCost;
    }

    public void setReceiveXgCost(String receiveXgCost) {
        this.receiveXgCost = receiveXgCost;
    }

    public String getReceiveQgCost() {
        return receiveQgCost;
    }

    public void setReceiveQgCost(String receiveQgCost) {
        this.receiveQgCost = receiveQgCost;
    }

    public String getShipJzCost() {
        return shipJzCost;
    }

    public void setShipJzCost(String shipJzCost) {
        this.shipJzCost = shipJzCost;
    }

    public String getReceiveShCost() {
        return receiveShCost;
    }

    public void setReceiveShCost(String receiveShCost) {
        this.receiveShCost = receiveShCost;
    }

    public String getSiteCost() {
        return siteCost;
    }

    public void setSiteCost(String siteCost) {
        this.siteCost = siteCost;
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

    public String getShipFhSite() {
        return shipFhSite;
    }

    public void setShipFhSite(String shipFhSite) {
        this.shipFhSite = shipFhSite;
    }

    public String getShipHyd() {
        return shipHyd;
    }

    public void setShipHyd(String shipHyd) {
        this.shipHyd = shipHyd;
    }

    public String getReceiveHyd() {
        return receiveHyd;
    }

    public void setReceiveHyd(String receiveHyd) {
        this.receiveHyd = receiveHyd;
    }

    public String getShipThType() {
        return shipThType;
    }

    public void setShipThType(String shipThType) {
        this.shipThType = shipThType;
    }

    public String getShipThTypeId() {
        return shipThTypeId;
    }

    public void setShipThTypeId(String shipThTypeId) {
        this.shipThTypeId = shipThTypeId;
    }

    public String getShipZsType() {
        return shipZsType;
    }

    public void setShipZsType(String shipZsType) {
        this.shipZsType = shipZsType;
    }

    public String getShipZsTypeId() {
        return shipZsTypeId;
    }

    public void setShipZsTypeId(String shipZsTypeId) {
        this.shipZsTypeId = shipZsTypeId;
    }

    public String getYuyan() {
        return yuyan;
    }

    public void setYuyan(String yuyan) {
        this.yuyan = yuyan;
    }

    public Date getTjTime() {
        return tjTime;
    }

    public void setTjTime(Date tjTime) {
        this.tjTime = tjTime;
    }

    public Date getTjFTime() {
        return tjFTime;
    }

    public void setTjFTime(Date tjFTime) {
        this.tjFTime = tjFTime;
    }

    public String getZxNumber() {
        return zxNumber;
    }

    public void setZxNumber(String zxNumber) {
        this.zxNumber = zxNumber;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    public String getIsPhone() {
        return IsPhone;
    }

    public void setIsPhone(String IsPhone) {
        this.IsPhone = IsPhone;
    }

    public String getZxThcostCurrency() {
        return zxThcostCurrency;
    }

    public void setZxThcostCurrency(String zxThcostCurrency) {
        this.zxThcostCurrency = zxThcostCurrency;
    }

    public String getShipThCostNo() {
        return shipThCostNo;
    }

    public void setShipThCostNo(String shipThCostNo) {
        this.shipThCostNo = shipThCostNo;
    }

    public String getYwNumber() {
        return ywNumber;
    }

    public void setYwNumber(String ywNumber) {
        this.ywNumber = ywNumber;
    }

    public String getClientYwNumber() {
        return clientYwNumber;
    }

    public void setClientYwNumber(String clientYwNumber) {
        this.clientYwNumber = clientYwNumber;
    }

    public String getIsGetin() {
        return IsGetin;
    }

    public void setIsGetin(String IsGetin) {
        this.IsGetin = IsGetin;
    }

    public Integer getTurnCount() {
        return TurnCount;
    }

    public void setTurnCount(Integer TurnCount) {
        this.TurnCount = TurnCount;
    }

    public Integer getTotalTurnCount() {
        return TotalTurnCount;
    }

    public void setTotalTurnCount(Integer TotalTurnCount) {
        this.TotalTurnCount = TotalTurnCount;
    }

    public BigDecimal getTotalTurnCountAvg() {
        return TotalTurnCountAvg;
    }

    public void setTotalTurnCountAvg(BigDecimal TotalTurnCountAvg) {
        this.TotalTurnCountAvg = TotalTurnCountAvg;
    }

    public String getActualClassdate() {
        return actualClassdate;
    }

    public void setActualClassdate(String actualClassdate) {
        this.actualClassdate = actualClassdate;
    }

    public Integer getYwFeedback() {
        return ywFeedback;
    }

    public void setYwFeedback(Integer ywFeedback) {
        this.ywFeedback = ywFeedback;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Integer getPutoffClass() {
        return putoffClass;
    }

    public void setPutoffClass(Integer putoffClass) {
        this.putoffClass = putoffClass;
    }

    public String getCostVerify() {
        return costVerify;
    }

    public void setCostVerify(String costVerify) {
        this.costVerify = costVerify;
    }

    public String getDcGaidanState() {
        return dcGaidanState;
    }

    public void setDcGaidanState(String dcGaidanState) {
        this.dcGaidanState = dcGaidanState;
    }

    public String getReceiveShCostId() {
        return receiveShCostId;
    }

    public void setReceiveShCostId(String receiveShCostId) {
        this.receiveShCostId = receiveShCostId;
    }

    public String getReceiveOrderEmail() {
        return receiveOrderEmail;
    }

    public void setReceiveOrderEmail(String receiveOrderEmail) {
        this.receiveOrderEmail = receiveOrderEmail;
    }

    public String getXgCostcurrency() {
        return xgCostcurrency;
    }

    public void setXgCostcurrency(String xgCostcurrency) {
        this.xgCostcurrency = xgCostcurrency;
    }

    public String getQgCostcurrency() {
        return qgCostcurrency;
    }

    public void setQgCostcurrency(String qgCostcurrency) {
        this.qgCostcurrency = qgCostcurrency;
    }

    public String getShCostcurrency() {
        return shCostcurrency;
    }

    public void setShCostcurrency(String shCostcurrency) {
        this.shCostcurrency = shCostcurrency;
    }

    public String getSitecostCurrency() {
        return sitecostCurrency;
    }

    public void setSitecostCurrency(String sitecostCurrency) {
        this.sitecostCurrency = sitecostCurrency;
    }

    public Date getExameTime() {
        return exameTime;
    }

    public void setExameTime(Date exameTime) {
        this.exameTime = exameTime;
    }

    public String getGdBoat() {
        return gdBoat;
    }

    public void setGdBoat(String gdBoat) {
        this.gdBoat = gdBoat;
    }

    public String getGdBoaturl() {
        return gdBoaturl;
    }

    public void setGdBoaturl(String gdBoaturl) {
        this.gdBoaturl = gdBoaturl;
    }

    public String getGdCjsremark() {
        return gdCjsremark;
    }

    public void setGdCjsremark(String gdCjsremark) {
        this.gdCjsremark = gdCjsremark;
    }

    public String getGdVoucherurl() {
        return gdVoucherurl;
    }

    public void setGdVoucherurl(String gdVoucherurl) {
        this.gdVoucherurl = gdVoucherurl;
    }

    public String getGdVoucher() {
        return gdVoucher;
    }

    public void setGdVoucher(String gdVoucher) {
        this.gdVoucher = gdVoucher;
    }

    public String getTrainState() {
        return trainState;
    }

    public void setTrainState(String trainState) {
        this.trainState = trainState;
    }

    public String getInquiryRecordId() {
        return inquiryRecordId;
    }

    public void setInquiryRecordId(String inquiryRecordId) {
        this.inquiryRecordId = inquiryRecordId;
    }

    @Override
    public String toString() {
        return "BusiShippingorder{" +
                "orderId=" + orderId +
                ", stationID=" + stationID +
                ", orderNumber=" + orderNumber +
                ", bookingService=" + bookingService +
                ", orderAuditBelongto=" + orderAuditBelongto +
                ", orderAuditCreatedate=" + orderAuditCreatedate +
                ", orderAuditUserid=" + orderAuditUserid +
                ", orderAuditUsername=" + orderAuditUsername +
                ", classId=" + classId +
                ", classDate=" + classDate +
                ", classSite=" + classSite +
                ", orderClassBh=" + orderClassBh +
                ", lineTypeid=" + lineTypeid +
                ", classNumber=" + classNumber +
                ", classEastandwest=" + classEastandwest +
                ", orderUploadsite=" + orderUploadsite +
                ", orderUnloadsite=" + orderUnloadsite +
                ", orderUploadcode=" + orderUploadcode +
                ", orderUnloadcode=" + orderUnloadcode +
                ", dictId=" + dictId +
                ", dictName=" + dictName +
                ", orderDeptname=" + orderDeptname +
                ", orderMerchandiser=" + orderMerchandiser +
                ", orderMerchandiserId=" + orderMerchandiserId +
                ", orderIsticket=" + orderIsticket +
                ", clientId=" + clientId +
                ", khId=" + khId +
                ", clientUnit=" + clientUnit +
                ", clientContacts=" + clientContacts +
                ", clientTel=" + clientTel +
                ", clientEmail=" + clientEmail +
                ", clientAddress=" + clientAddress +
                ", clientOrderBindingaddress=" + clientOrderBindingaddress +
                ", clientOrderBindingway=" + clientOrderBindingway +
                ", clientOrderRemarks=" + clientOrderRemarks +
                ", containerBoxamount=" + containerBoxamount +
                ", containerType=" + containerType +
                ", containerTypeval=" + containerTypeval +
                ", clientOrderRepaycontainer=" + clientOrderRepaycontainer +
                ", shipOrderIsdispatch=" + shipOrderIsdispatch +
                ", shipOrderId=" + shipOrderId +
                ", shipOrederName=" + shipOrederName +
                ", shipOrederContacts=" + shipOrederContacts +
                ", shipOrederEmail=" + shipOrederEmail +
                ", shipOrederPhone=" + shipOrederPhone +
                ", shipOrederAddress=" + shipOrederAddress +
                ", shipOrderBinningway=" + shipOrderBinningway +
                ", shipOrderUnloadaddress=" + shipOrderUnloadaddress +
                ", shipOrderUnloadcontacts=" + shipOrderUnloadcontacts +
                ", shipOrderUnloadway=" + shipOrderUnloadway +
                ", shipOrderUnloadwayEmail=" + shipOrderUnloadwayEmail +
                ", shipOrderUnloadtime=" + shipOrderUnloadtime +
                ", shipOrderSendtime=" + shipOrderSendtime +
                ", receiveOrderId=" + receiveOrderId +
                ", receiveOrderName=" + receiveOrderName +
                ", receiveOrderContacts=" + receiveOrderContacts +
                ", receiveOrderMail=" + receiveOrderMail +
                ", receiveOrderPhone=" + receiveOrderPhone +
                ", receiveOrderAddress=" + receiveOrderAddress +
                ", receiveOrderAddress=" + receiveOrderAddress +
                ", receiveOrderIsclearance=" + receiveOrderIsclearance +
                ", receiveOrderIspart=" + receiveOrderIspart +
                ", distributionType=" + distributionType +
                ", receiveOrderZihemail=" + receiveOrderZihemail +
                ", receiveOrderFbStatus=" + receiveOrderFbStatus +
                ", receiveOrderPartaddress=" + receiveOrderPartaddress +
                ", receiveOrderReceiveemail=" + receiveOrderReceiveemail +
                ", receiveOrderEnName=" + receiveOrderEnName +
                ", receiveOrderEnContacts=" + receiveOrderEnContacts +
                ", receiveOrderEnEmail=" + receiveOrderEnEmail +
                ", receiveOrderEnPhone=" + receiveOrderEnPhone +
                ", receiveOrderEnAddress=" + receiveOrderEnAddress +
                ", receiveOrderEname=" + receiveOrderEname +
                ", receiveOrderEcontacts=" + receiveOrderEcontacts +
                ", receiveOrderEemail=" + receiveOrderEemail +
                ", receiveOrderEphone=" + receiveOrderEphone +
                ", receiveOrderEaddress=" + receiveOrderEaddress +
                ", etxCompany=" + etxCompany +
                ", Eduty=" + Eduty +
                ", etxName=" + etxName +
                ", receiveOrderZihcontacts=" + receiveOrderZihcontacts +
                ", receiveOrderZihtel=" + receiveOrderZihtel +
                ", goodsCannotpileup=" + goodsCannotpileup +
                ", goodsFragile=" + goodsFragile +
                ", goodsGeneral=" + goodsGeneral +
                ", IsExamline=" + IsExamline +
                ", IsConsolidation=" + IsConsolidation +
                ", createdate=" + createdate +
                ", createuserid=" + createuserid +
                ", createusername=" + createusername +
                ", shipThCost=" + shipThCost +
                ", clientBgCost=" + clientBgCost +
                ", receiveHxAddress=" + receiveHxAddress +
                ", receiveXgCost=" + receiveXgCost +
                ", receiveQgCost=" + receiveQgCost +
                ", shipJzCost=" + shipJzCost +
                ", receiveShCost=" + receiveShCost +
                ", siteCost=" + siteCost +
                ", clientTjr=" + clientTjr +
                ", clientTjrId=" + clientTjrId +
                ", shipFhSite=" + shipFhSite +
                ", shipHyd=" + shipHyd +
                ", receiveHyd=" + receiveHyd +
                ", shipThType=" + shipThType +
                ", shipThTypeId=" + shipThTypeId +
                ", shipZsType=" + shipZsType +
                ", shipZsTypeId=" + shipZsTypeId +
                ", yuyan=" + yuyan +
                ", tjTime=" + tjTime +
                ", tjFTime=" + tjFTime +
                ", zxNumber=" + zxNumber +
                ", isupdate=" + isupdate +
                ", IsPhone=" + IsPhone +
                ", zxThcostCurrency=" + zxThcostCurrency +
                ", shipThCostNo=" + shipThCostNo +
                ", ywNumber=" + ywNumber +
                ", clientYwNumber=" + clientYwNumber +
                ", IsGetin=" + IsGetin +
                ", TurnCount=" + TurnCount +
                ", TotalTurnCount=" + TotalTurnCount +
                ", TotalTurnCountAvg=" + TotalTurnCountAvg +
                ", actualClassdate=" + actualClassdate +
                ", ywFeedback=" + ywFeedback +
                ", detailedAddress=" + detailedAddress +
                ", putoffClass=" + putoffClass +
                ", costVerify=" + costVerify +
                ", dcGaidanState=" + dcGaidanState +
                ", receiveShCostId=" + receiveShCostId +
                ", receiveOrderEmail=" + receiveOrderEmail +
                ", xgCostcurrency=" + xgCostcurrency +
                ", qgCostcurrency=" + qgCostcurrency +
                ", shCostcurrency=" + shCostcurrency +
                ", sitecostCurrency=" + sitecostCurrency +
                ", exameTime=" + exameTime +
                ", gdBoat=" + gdBoat +
                ", gdBoaturl=" + gdBoaturl +
                ", gdCjsremark=" + gdCjsremark +
                ", gdVoucherurl=" + gdVoucherurl +
                ", gdVoucher=" + gdVoucher +
                ", trainState=" + trainState +
                ", inquiryRecordId=" + inquiryRecordId +
                ", limitation=" + limitation +
                ", truckType=" + truckType +
                ", consignorc=" + consignorc +
                ", domesticNumber=" + domesticNumber +
                ", foreignNumber=" + foreignNumber +
                "}";
    }
}