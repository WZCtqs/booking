package com.zih.booking.vo;
//class_id						VARCHAR(50),		--班列ID
//class_no　						VARCHAR(50),		--班列号
//class_east_west				INT,				--0:去程，1:返程
//class_depature_time			DATETIME,			--班列发车日
//class_departure_city			VARCHAR(50),		--班列始发站
//
//order_id						VARCHAR(50),		--委托书ID
//order_number					VARCHAR(50),		--委托书编号
//
//container_quantity			INT,				--箱量
//container_type				VARCHAR(50),		--箱型
//load_station					VARCHAR(50),		--上货站
//unload_station				VARCHAR(50),		--下货站
//
//--biz_dept_name				VARCHAR(500),		--业务部门
//client_tjr					VARCHAR(500),		--推荐人
//declarant 					VARCHAR(50),		--跟单员
//declarant_email				VARCHAR(500),		--跟单员邮箱
//
//customer						VARCHAR(500),		--订舱方
//ship_customer					VARCHAR(500),		--发货方
//fh_city						VARCHAR(500),		--发货城市
//is_need_pick_goods			INT,				--装箱方式
//self_send_time				DATETIME,			--自送货时间
//goods_pick_deadline			DATETIME,			--提货时间
//goods_pick_addr				VARCHAR(500),		--提货地址
//
//dh_city						VARCHAR(500),		--到货城市
//is_need_send_goods			INT,				--分拨方式，ZIH代理送货
//hxd							VARCHAR(500),		--还箱地
//xg_fee						varchar(500),		--DECIMAL(15, 2),		--箱管费
//goods_types					VARCHAR(500),		--货物品名
//goods_types_en				VARCHAR(500),		--货物品名
//goods_packing					VARCHAR(500),		--包装形式
//goods_number					INT,				--包装数量
//goods_volume					DECIMAL(15, 3),		--货物体积
//goods_weight					DECIMAL(15, 3),		--货物重量
//
//examine_time					DATETIME,			--订单审核时间,
//change_reason					VARCHAR(2000)		--舱位变更原因


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ToString
public class Cabin implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private String id;
    /** 班列id */
    private String classId;
    /** 班列号 */
    private String classNo;
    /** 班列编号 */
    private String classBh;
    /** 0为去程 1为回程 */
    private Integer classEastWest;
    /** 班列发车日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date classDepatureTime;
    /** 发车城市 */
    private String classDepartureCity;
    /** 班列目的城市 */
    private String classDestinationCity;
    /** 0拼箱 1独立箱 */
    private Integer isIndependent;

    private Integer isNeedContainer;
    /** 订单id */
    private String orderId;
    /** 订单号 */
    private String orderNumber;
    /** 重箱托书编号 */
    private String preOrderNumber;
    /** 箱量 */
    private Integer containerQuantity;
    /** 箱型 */
    private String containerType;
    /** 上货站编码 */
    private String loadStation;
    /** 下货站编码 */
    private String unloadStation;

    private String bizDeptName;
    /** 客户推荐人 */
    private String clientTjr;
    /** 跟单姓名 */
    private String declarant;
    /** 跟单邮箱 */
    private String declarantEmail;
    /** 订舱方名称 */
    private String customer;
    /** 发货方 */
    private String shipCustomer;
    /** 提箱地 */
    private String fhCity;
    /** 委托ZIH提货 0是 1否 */
    private Integer isNeedPickGoods;
    /** 提货方式（整箱到车站，散货到堆场） */
    private String thType;

    private String lenderId;



    // ---------------------------------------------------------
    /** 自送货时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date selfSendTime;
    /** 提货时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsPickDeadline;
    /** 提货地址 */
    private String goodsPickAddr;
    /** 到货城市 */
    private String dhCity;
    /** 分拨方式 */
    private Integer isNeedSendGoods;
    /** 还箱地 */
    private String hxd;
    /** 箱管费 */
    private String xgFee;
    /** 货品中文名称 */
    private String goodsTypes;
    /** 货品英文名称 */
    private String goodsTypesEn;
    /** 最外层包装形式 */
    private String goodsPacking;
    /** 最外层包装数量 */
    private String goodsNumber;
    /** 体积 */
    private String goodsVolume;
    /** 重量 */
    private String goodsWeight;
    /** 审核时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss SSS")
    private Date examineTime;

    private String changeReason;
    /** 货物备注 */
    private String remark;

    private String comment;

    private String preClassId;
    /** 集装箱箱属(0委托ZIH1自备) */
    private String orderAuditBelongTo;

    private String detailedAddress;

    private String receiveOrderAddr;

    private String recommendYard;
    /** 线路类型：0是中欧2是中亚3是中越4是中俄 */
    private Integer lineTypeid;
    /** 0未审核 1已审核通过 2已审核未通过 3已取消的委托 4转待审核 5草稿 6转待失败 */
    private String isExamline;

    /** 0 ,1自建 */
    private String selfBuild;

    /** 客户id */
    private String clientId;

    /** 0 insert、1 update、2 delete */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelfBuild() {
        return selfBuild;
    }

    public void setSelfBuild(String selfBuild) {
        this.selfBuild = selfBuild;
    }

    public String getIsExamline() {
        return isExamline;
    }

    public void setIsExamline(String isExamline) {
        this.isExamline = isExamline;
    }

    public Integer getLineTypeid() {
        return lineTypeid;
    }

    public void setLineTypeid(Integer lineTypeid) {
        this.lineTypeid = lineTypeid;
    }

    public String getRecommendYard() {
        return recommendYard;
    }

    public void setRecommendYard(String recommendYard) {
        this.recommendYard = recommendYard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public Integer getClassEastWest() {
        return classEastWest;
    }

    public void setClassEastWest(Integer classEastWest) {
        this.classEastWest = classEastWest;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getClassDepatureTime() {
        return classDepatureTime;
    }

    public void setClassDepatureTime(Date classDepatureTime) {
        this.classDepatureTime = classDepatureTime;
    }

    public String getClassDepartureCity() {
        return classDepartureCity;
    }

    public void setClassDepartureCity(String classDepartureCity) {
        this.classDepartureCity = classDepartureCity;
    }

    public String getClassDestinationCity() {
        return classDestinationCity;
    }

    public void setClassDestinationCity(String classDestinationCity) {
        this.classDestinationCity = classDestinationCity;
    }

    public Integer getIsIndependent() {
        return isIndependent;
    }

    public void setIsIndependent(Integer isIndependent) {
        this.isIndependent = isIndependent;
    }

    public Integer getIsNeedContainer() {
        return isNeedContainer;
    }

    public void setIsNeedContainer(Integer isNeedContainer) {
        this.isNeedContainer = isNeedContainer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getContainerQuantity() {
        return containerQuantity;
    }

    public void setContainerQuantity(Integer containerQuantity) {
        this.containerQuantity = containerQuantity;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getLoadStation() {
        return loadStation;
    }

    public void setLoadStation(String loadStation) {
        this.loadStation = loadStation;
    }

    public String getUnloadStation() {
        return unloadStation;
    }

    public void setUnloadStation(String unloadStation) {
        this.unloadStation = unloadStation;
    }

    public String getBizDeptName() {
        return bizDeptName;
    }

    public void setBizDeptName(String bizDeptName) {
        this.bizDeptName = bizDeptName;
    }

    public String getClientTjr() {
        return clientTjr;
    }

    public void setClientTjr(String clientTjr) {
        this.clientTjr = clientTjr;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public String getDeclarantEmail() {
        return declarantEmail;
    }

    public void setDeclarantEmail(String declarantEmail) {
        this.declarantEmail = declarantEmail;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShipCustomer() {
        return shipCustomer;
    }

    public void setShipCustomer(String shipCustomer) {
        this.shipCustomer = shipCustomer;
    }

    public String getFhCity() {
        return fhCity;
    }

    public void setFhCity(String fhCity) {
        this.fhCity = fhCity;
    }

    public Integer getIsNeedPickGoods() {
        return isNeedPickGoods;
    }

    public void setIsNeedPickGoods(Integer isNeedPickGoods) {
        this.isNeedPickGoods = isNeedPickGoods;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getSelfSendTime() {
        return selfSendTime;
    }

    public void setSelfSendTime(Date selfSendTime) {
        this.selfSendTime = selfSendTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getGoodsPickDeadline() {
        return goodsPickDeadline;
    }

    public void setGoodsPickDeadline(Date goodsPickDeadline) {
        this.goodsPickDeadline = goodsPickDeadline;
    }

    public String getGoodsPickAddr() {
        return goodsPickAddr;
    }

    public void setGoodsPickAddr(String goodsPickAddr) {
        this.goodsPickAddr = goodsPickAddr;
    }

    public String getDhCity() {
        return dhCity;
    }

    public void setDhCity(String dhCity) {
        this.dhCity = dhCity;
    }

    public Integer getIsNeedSendGoods() {
        return isNeedSendGoods;
    }

    public void setIsNeedSendGoods(Integer isNeedSendGoods) {
        this.isNeedSendGoods = isNeedSendGoods;
    }

    public String getHxd() {
        return hxd;
    }

    public void setHxd(String hxd) {
        this.hxd = hxd;
    }

    public String getXgFee() {
        return xgFee;
    }

    public void setXgFee(String xgFee) {
        this.xgFee = xgFee;
    }

    public String getGoodsTypes() {
        return goodsTypes;
    }

    public void setGoodsTypes(String goodsTypes) {
        this.goodsTypes = goodsTypes;
    }

    public String getGoodsTypesEn() {
        return goodsTypesEn;
    }

    public void setGoodsTypesEn(String goodsTypesEn) {
        this.goodsTypesEn = goodsTypesEn;
    }

    public String getGoodsPacking() {
        return goodsPacking;
    }

    public void setGoodsPacking(String goodsPacking) {
        this.goodsPacking = goodsPacking;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS", timezone = "GMT+8")
    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getPreClassId() {
        return preClassId;
    }

    public void setPreClassId(String preClassId) {
        this.preClassId = preClassId;
    }



    public String getLenderId() {
        return lenderId;
    }

    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    // 得到舱位标箱量
    public Integer getTeus() {
        if (this.getContainerQuantity() == 0 || this.containerType == null || this.containerType.equalsIgnoreCase(""))
            return 0;

        Integer teus = -1;
        try {
            teus = this.containerQuantity * Integer.parseInt(this.containerType.substring(0, 1)) / 2;
        } catch (Exception e) {
            return -1;
        }
        return teus;
    }

    public String getPreOrderNumber() {
        return preOrderNumber;
    }

    public void setPreOrderNumber(String preOrderNumber) {
        this.preOrderNumber = preOrderNumber;
    }

    public String getThType() {
        return thType;
    }

    public void setThType(String thType) {
        this.thType = thType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderAuditBelongTo() {
        return orderAuditBelongTo;
    }

    public void setOrderAuditBelongTo(String orderAuditBelongTo) {
        this.orderAuditBelongTo = orderAuditBelongTo;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getReceiveOrderAddr() {
        return receiveOrderAddr;
    }

    public void setReceiveOrderAddr(String receiveOrderAddr) {
        this.receiveOrderAddr = receiveOrderAddr;
    }

    public String getClassBh() {
        return classBh;
    }

    public void setClassBh(String classBh) {
        this.classBh = classBh;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
