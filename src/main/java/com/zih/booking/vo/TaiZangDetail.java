package com.zih.booking.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data//JsonProperty主要是jackson解析会自动首字母大写转小写，然后已经跟前端按照大写对完接口了，就加了这个注解 美金总金额 按小写对的。。。
public class TaiZangDetail {
    /**
     * 客户前台费用确认单显示其他费用备注的权限，0是显示，1不显示
     */
    private String ISRemark;
    //费用单id
    @JsonProperty("Taizang_id")
    private String Taizang_id;
    //线路id（0中欧2中亚，3中越，4中俄）
    private String    typeid;
    //往返
    private String   class_EastAndWest;
    //整拼箱
    @JsonProperty("IsConsolidation")
    private  String IsConsolidation;
    // 班列编号
    private String class_bh;
    //（拼箱箱型）
    private String  xiangxing;
    //To 订舱方
    private String client_unit;
    //班列开车时间
    private String class_date;
    //柜型/箱型
    private String container_type;
    //柜量
    private String container_boxAmount;
    //始发站
    private String order_uploadSite;
    //终到站
    private String order_unloadSite;
    //舱位号:
    private String order_number;
    //集装箱号
    private String container_no;
    //货物品名
    private String goods_name;
    //计费体积
    private String px_Settlement_volumet;
    //超重费
    private String overweightcost;
    //报关费
    @JsonProperty("BG_INCOMES")
    private String BG_INCOMES;
    //铁路运费
    private String siteIncome;
    //铁路运费币种
    private String siteIncomeCurrency;
    //提货费
    @JsonProperty("TH_INCOMES")
    private String TH_INCOMES;
    //提货费币种
    @JsonProperty("TH_INCOMESCurrency")
    private String TH_INCOMESCurrency;
    //分拨费
    @JsonProperty("SH_INCOMES")
    private String SH_INCOMES;
    //分拨费币种
    @JsonProperty("SH_INCOMESCurrency")
    private String SH_INCOMESCurrency;
    //保险费
    private String  bxcostfund;
    //其他费用
    private String taizang_other_income;
    //优惠返利
    private String discounts;
    //优惠返利币种
    private String discountsCurrency;
    //返利月分
    private String discounts_month;
    //整柜散货公路运费
    @JsonProperty("PX_roadincome")
    private String PX_roadincome;
    //装箱加固费
    @JsonProperty("Pinup_cost")
    private String Pinup_cost;
    //提、还箱平衡费
    @JsonProperty("HUANXIANG")
    private String HUANXIANG;
    //总计
    @JsonProperty("TotalmoneyS")
    private String TotalmoneyS;
    //美元兑人民币
    @JsonProperty("USDtoRMB")
    private String USDtoRMB;
    //欧元兑人民币
    @JsonProperty("EURtoRMB")
    private String EURtoRMB;
    //欧元兑美元:
    @JsonProperty("EUTtoUSD")
    private String EUTtoUSD;
    //其他费用明细
    @JsonProperty("Remark_other_income")
    private String Remark_other_income;
    //欧元金额
    @JsonProperty("EURTotalS")
    private String EURTotalS;
    //美元金额
    @JsonProperty("USDTotalS")
    private String USDTotalS;
    //人民币金额
    @JsonProperty("RMBTotalS")
    private String RMBTotalS;
    //美元合计金额
     private String Totalusd;
    //人民币开票金额
    private String kp_rmb;
    //美元开票金额
    private String kp_usd;
    //欧元开票金额:
    private String kp_eur;
    //开票要求
    @JsonProperty("Remark_kp")
    private String Remark_kp;


}
