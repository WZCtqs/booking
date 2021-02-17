package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zih.booking.system.annotation.PropertyMsg;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-30
 */
@TableName("busi_shippingorder_goods")
public class BusiShippingorderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID，主键
     */
    @TableId(value = "goods_ID", type = IdType.UUID)
    private String goodsId;
    /**
     * 订舱表ID
     */
    @TableField("order_ID")
    private String orderId;
    /**
     * 唛头
     */
    @PropertyMsg("唛头")
    @TableField("goods_mark")
    private String goodsMark;
    /**
     * 货品中文名称
     */
    @PropertyMsg("货品中文名称")
    @TableField("goods_name")
    private String goodsName;
    /**
     * 货品英文名称
     */
    @PropertyMsg("货品英文名称")
    @TableField("goods_en_name")
    private String goodsEnName;
    /**
     * 国外报关HS
     */
    @PropertyMsg("国外报关HS")
    @TableField("goods_report")
    private String goodsReport;
    /**
     * 国外清关HS
     */
    @PropertyMsg("国外清关HS")
    @TableField("goods_out_clearance")
    private String goodsOutClearance;
    /**
     * 国内报关
     */
    @PropertyMsg("国内报关")
    @TableField("goods_in_report")
    private String goodsInReport;
    /**
     * 国内清关
     */
    @PropertyMsg("国内清关")
    @TableField("goods_clearance")
    private String goodsClearance;
    /**
     * 最外层包装形式
     */
    @PropertyMsg("最外层包装形式")
    @TableField("goods_packing")
    private String goodsPacking;
    /**
     * 最外层包装数量
     */
    @PropertyMsg("最外层包装数量")
    @TableField("goods_number")
    private String goodsNumber;
    /**
     * 规格
     */
    @TableField("goods_standard")
    private String goodsStandard;
    /**
     * 重量
     */
    @PropertyMsg("重量")
    @TableField("goods_KGS")
    private String goodsKgs;
    /**
     * 体积
     */
    @PropertyMsg("体积")
    @TableField("goods_CBM")
    private String goodsCbm;
    /**
     * 是否需要装箱方案：0需要 1不需要
     */
    @TableField("goods_isScheme")
    private String goodsIsscheme;
    /**
     * 备注
     */
    @PropertyMsg("备注")
    private String remark;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 托书修改记录
     */
    @TableField("goods_history_editrecord")
    private String goodsHistoryEditrecord;
    /**
     * 货物备注
     */
    @PropertyMsg("货物备注")
    private String goodsbz;
    /**
     * 货物hs编码备注
     */
    @PropertyMsg("货物hs编码备注")
    private String HSbz;
    /**
     * 是否含有放射性
     */
    private String radioaction;
    /**
     * 业务开票状态1是已开票
     */
    @TableField("kp_state")
    private String kpState;


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsMark() {
        return goodsMark;
    }

    public void setGoodsMark(String goodsMark) {
        this.goodsMark = goodsMark;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsEnName() {
        return goodsEnName;
    }

    public void setGoodsEnName(String goodsEnName) {
        this.goodsEnName = goodsEnName;
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

    public String getGoodsStandard() {
        return goodsStandard;
    }

    public void setGoodsStandard(String goodsStandard) {
        this.goodsStandard = goodsStandard;
    }

    public String getGoodsKgs() {
        return goodsKgs;
    }

    public void setGoodsKgs(String goodsKgs) {
        this.goodsKgs = goodsKgs;
    }

    public String getGoodsCbm() {
        return goodsCbm;
    }

    public void setGoodsCbm(String goodsCbm) {
        this.goodsCbm = goodsCbm;
    }

    public String getGoodsIsscheme() {
        return goodsIsscheme;
    }

    public void setGoodsIsscheme(String goodsIsscheme) {
        this.goodsIsscheme = goodsIsscheme;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getGoodsHistoryEditrecord() {
        return goodsHistoryEditrecord;
    }

    public void setGoodsHistoryEditrecord(String goodsHistoryEditrecord) {
        this.goodsHistoryEditrecord = goodsHistoryEditrecord;
    }

    public String getGoodsbz() {
        return goodsbz;
    }

    public void setGoodsbz(String goodsbz) {
        this.goodsbz = goodsbz;
    }

    public String getHSbz() {
        return HSbz;
    }

    public void setHSbz(String HSbz) {
        this.HSbz = HSbz;
    }

    public String getRadioaction() {
        return radioaction;
    }

    public void setRadioaction(String radioaction) {
        this.radioaction = radioaction;
    }

    public String getKpState() {
        return kpState;
    }

    public void setKpState(String kpState) {
        this.kpState = kpState;
    }



    public String getGoodsOutClearance() {
        return goodsOutClearance;
    }

    public void setGoodsOutClearance(String goodsOutClearance) {
        this.goodsOutClearance = goodsOutClearance;
    }

    public String getGoodsInReport() {
        return goodsInReport;
    }

    public void setGoodsInReport(String goodsInReport) {
        this.goodsInReport = goodsInReport;
    }


    public String getGoodsReport() {
        return goodsReport;
    }

    public void setGoodsReport(String goodsReport) {
        this.goodsReport = goodsReport;
    }

    public String getGoodsClearance() {
        return goodsClearance;
    }

    public void setGoodsClearance(String goodsClearance) {
        this.goodsClearance = goodsClearance;
    }

    @Override
    public String toString() {
        return "BusiShippingorderGoods{" +
                "goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", goodsMark='" + goodsMark + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsEnName='" + goodsEnName + '\'' +
                ", goodsReport='" + goodsReport + '\'' +
                ", goodsOutClearance='" + goodsOutClearance + '\'' +
                ", goodsInReport='" + goodsInReport + '\'' +
                ", goodsClearance='" + goodsClearance + '\'' +
                ", goodsPacking='" + goodsPacking + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", goodsStandard='" + goodsStandard + '\'' +
                ", goodsKgs='" + goodsKgs + '\'' +
                ", goodsCbm='" + goodsCbm + '\'' +
                ", goodsIsscheme='" + goodsIsscheme + '\'' +
                ", remark='" + remark + '\'' +
                ", createdate=" + createdate +
                ", goodsHistoryEditrecord='" + goodsHistoryEditrecord + '\'' +
                ", goodsbz='" + goodsbz + '\'' +
                ", HSbz='" + HSbz + '\'' +
                ", radioaction='" + radioaction + '\'' +
                ", kpState='" + kpState + '\'' +
                '}';
    }
}
