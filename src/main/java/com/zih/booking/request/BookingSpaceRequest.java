package com.zih.booking.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data//在线订舱
@EqualsAndHashCode(callSuper = true)
public class BookingSpaceRequest extends BaseRequest {
    /** 客户每次托书提交时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date tjTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderAuditCreatedate;
    private String orderId;
    private String consignorc;
    private String etxCompany;
    private String econsignorstate;
    private String pickUpBoxFee;
    private String returnBoxFee;
    private String isExamline;
    private String shipZsType;
    private String shipZsTypeId;
    private String inquiryNum; //报价编码
    private String inquiryRecordId;
    private String limitation; /** 时效（箱型亚欧，0普通，1加急） */
    private String truckType;  /** 国内公路运输车辆类型，0普通车、1普通卡车、2白卡专车 */
    private String orderMerchandiser; //跟单员
    private String orderMerchandiserId; //跟单员id
    private String domesticNumber; //国内拼箱场站报价单号
    private String foreignNumber; //国外拼箱场站报价单号
    //班列信息 -----------START-----------
    private String classId;//****** 班列id *****
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date classDate;//班列日期

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date classEtime; //班列预计到站时间

    private String classEastandwest;//往返 0为去程 1为回程
    @JsonProperty("isconsolidation")
    private String IsConsolidation;// 整拼箱 0整柜 1拼箱
    private String classNumber;//班列号
    private Integer putoffClass;// 是否可提前班列（0是1否）
    private String orderUploadsite;//上货站
    private String orderUploadcode;
    private String orderUnloadsite;//下货站
    private String orderUnloadcode;
    private Integer dictId;//贸易方式（条款ID）
    private String dictName;//*********** 条款名称**********
    //  private String orderIsTicket;//需要发票
    @JsonProperty("sitecost")
    private String siteCost;//站到站运费
    private String sitecostCurrency;
    private String clientTjr;//推荐人
    private String clientTjrId;
    private String lineType;// 【询价】 线路类型（0郑欧（中欧）、2郑中亚（中亚）、3郑东盟(中越)）、4郑俄（中俄）
    private String orderClassBh;
    private String orderIsticket;
    private String bookingService;
    private String language;
    //班列信息 ------------END-------------


    //订舱方信息 -----------START-----------
    private String clientId;
    private Long khId; //客户子账号id
    private String clientUnit;//订舱方名称
    private String clientContacts;//联系人
    private String clientTel;//联系电话
    private String clientEmail;//邮箱
    private String clientAddress;//订舱方地址
    private String clientOrderBindingway;//委托ZIH报关 0是 1否
    private String clientBgCost;//报关费
    private String clientOrderBindingaddress;//报关地点
    private String ywNumber;//业务员编号
    private String clientYwNumber; //业务编号
    private String containerType;//集装箱箱型
    private String containerTypeval;//*********** 箱型值 ***********
    private String containerBoxamount;//集装箱数量
    private String orderAuditBelongto;//集装箱箱属 0委托ZIH 1自备 2自备铁路箱 3 自备非铁路箱
    private String goodsGeneral;//单件超长超重（1是0否）
    private String goodsCannotpileup;//是否可堆叠（1是0否2仅可自叠）
    private String goodsFragile;//是否易碎 1是0否
    //订舱方信息 ------------END------------


    //发货方信息 -----------START-----------
    private String senderId;//********* 发货方Id ********
    private String shipOrederName;//发货方
    private String shipOrederAddress;//通讯地址
    private String shipOrederContacts;//联系人
    private String shipOrederPhone;//联系方式
    private String shipOrederEmail;//邮箱
    private String shipThTypeId;//委托ZIH提货（0是整箱到车站，1是散货到堆场）针对整柜
    private String shipThType;//提货方式 （0整箱到车站，1散货到堆场）针对整柜
    private String shipOrderBinningway;//由ZIH提货 0是 1否  2铁路到货
    private String shipFhSite;//回程发货提箱地
    private String shipHyd;//去程发货提箱地

    private String actSenderId;//******实际发货方id****
    private String shipOrderUnloadcontacts;//提货联系人
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date shipOrderUnloadtime;//提货时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date shipOrderSendtime;//  自送货时间
    private String shipOrderUnloadway;//联系电话
    private String shipOrderUnloadaddress;//提货地址
    private String detailedAddress;//去程提货地，回程送货地详细地址（去回程）
    private String shipOrderUnloadwayEmail;//提货联系邮箱
    private String shipThCost;//提货费用
    private String shipThCostNo;//提货费报价编号
    private String zxThcostCurrency;
    private String senderProvince;//省
    private String senderCity;//市
    private String senderArea;//区
    //发货方信息 ------------END------------


    //收货方信息  -----------START-----------
    private String receiveId;//收货方id
    private String receiveOrderName;//收货方
    private String receiveOrderContacts;//联系人
    private String receiveOrderPhone;//联系电话Tel
    private String receiveOrderEmail;//邮箱
    private String receiveOrderAddress;//通讯地址
    private String receiveOrderZihcontacts;//送货联系人
    private String receiveOrderZihemail;//送货Email
    private String receiveOrderZihtel;//送货电话
    private String receiveOrderPartaddress;//送货分拨地址
    private String receiveOrderIsclearance;//ZIH代理清关   0否 1是
    private String receiveQgCost;//清关费用
    private String qgCostcurrency;//清关费币种
    private String receiveOrderIspart;//由ZIH送货  0否 1是
    private String distributionType; //分拨方式（0整柜派送，1拆箱散货派送)
    private String receiveHxAddress;//还箱地
    private String receiveXgCost;//箱管费
    private String receiveShCost;//送货费用
    private String receiveShCostId;
    private String shCostcurrency;
    private String actReceiverId;//******实际收货方id****
    private String receiveOrderReceiveemail;//在途信息接收邮箱
    private String receiveOrderMail;//到站通知提货邮箱
    private String etxName;//承担监管区费用的公司（或个人）名称
    private String eduty;//到货提箱公司税号
    private String receiveOrderEnName;//收货方（英文）（俄线回程整柜）
    private String receiveOrderEnAddress;//通讯地址（英文）
    private String receiveOrderEnContacts;//联系人（英文）

    private String receiveOrderEname;//收货方（俄文）
    private String receiveOrderEcontacts;//联系人（俄文）
    private String receiveOrderEaddress;//通讯地址（俄文）

    private String receiveProvince;//省
    private String receiveCity;//市
    private String receiveArea;//区
    //收货方信息 ------------END-------------

    //货物信息  -----------START-----------
    private String goodsId;
    private String goodsMark;//唛头
    private String goodsName;//货品名称(中)
    private String goodsEnName;//货品名称（英）
    private String goodsReport;//国外报关HS
    private String goodsOutClearance;//国外清关HS
    private String goodsInReport;//国内报关
    private String goodsClearance;//国内清关
    private String goodsPacking;//最外层包装形式
    private String goodsNumber;//最外层包装数量
    private String goodsCbm;//总体积
    private String goodsKgs;//总毛重
    private String goodsIsscheme;//装箱方案 0需要 1不需要
    private String shipJzCost; //监装费用
    private String shipOrderIsdispatch;//是否派监装员  0否 1是
    private String goodsbz;//其他要求备注
    private String HSbz; //货物hs编码备注
    private String goodsStandard;//规格
    private Integer specialId;// *******特殊单证物品id *********
    private String goodsname;//特殊单证物品
    private String radioaction;//是否含放射性
    private String specialnotes;//口岸或者上下货站是否有限制的备注
    private String remark;
    //货物详情
    private String goodsInfoDetail;
    //货物信息 ------------END-------------

}
