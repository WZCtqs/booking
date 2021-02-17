package com.zih.booking.utils;

import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.BusiShippingorderGoods;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class SendOrderMailUtil {
    //邮件内容
    public static String chOrderEmailContent(BusiShippingorder busiShippingorder, BusiShippingorderGoods busiShippingorderGoods){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String classDate = "";
        if(null != busiShippingorder.getClassDate()){
            classDate = dateFormat.format(busiShippingorder.getClassDate());
        }
        sb.append("班列日期:" + classDate + "\n");
        sb.append("业务编号:" + busiShippingorder.getClientYwNumber() + "\n");
        sb.append("订舱单位:" + busiShippingorder.getClientUnit()+ ",  " + "订舱人:" + busiShippingorder.getClientContacts() + "\n");
        sb.append("上货站:" + busiShippingorder.getOrderUploadsite() + ",  " + "下货站:" + busiShippingorder.getOrderUnloadsite()+ "\n");
        sb.append("货品名称:" + busiShippingorderGoods.getGoodsName() + "\n");
        sb.append("发货方:" + busiShippingorder.getShipOrederName() + "\n");
        sb.append("收货方:" + busiShippingorder.getReceiveOrderName() + "\n");
        sb.append("最外层包装形式:" + busiShippingorderGoods.getGoodsPacking() + ",  " + "最外层包装数量:" + busiShippingorderGoods.getGoodsNumber() + "\n");
        sb.append("总体积(CBM):" +busiShippingorderGoods.getGoodsCbm() + ",  " + "总重量(KGS):" + busiShippingorderGoods.getGoodsKgs() + "\n");
        //整柜
        if("0".equals(busiShippingorder.getIsConsolidation())){
            String containerType = busiShippingorder.getContainerType();
            if(null !=containerType && containerType !=""){
                switch (containerType)
                {
                    case "20GP":containerType = "20尺普箱";break;
                    case "20HC":containerType = "20尺高箱";break;
                    case "40GP":containerType = "40尺普箱";break;
                    case "40HC":containerType = "40尺高箱";break;
                    case "45HC":containerType = "45尺高箱";break;
                    case "20HOT":containerType = "20尺超高开顶箱";break;
                    case "20HT":containerType = "20尺挂衣箱";break;
                    case "20OT":containerType = "20尺普高开顶箱";break;
                    case "40HOT":containerType = "40尺超高开顶箱";break;
                    case "40HT":containerType = "40尺挂衣箱";break;
                    case "40MT":containerType = "40尺分层箱";break;
                    case "40OT":containerType = "40尺普高开顶箱";break;
                    case "40RF":containerType = "40尺冷藏";break;
                    case "45RF":containerType = "45尺冷藏箱";break;
                }
            }
            sb.append("箱量:" + busiShippingorder.getContainerBoxamount() + ",  " + "箱型:" + containerType + "\n");
        }
        if ("0".equals(busiShippingorder.getShipOrderBinningway() )&& "1".equals(busiShippingorder.getIsExamline())) {
            sb.append("提货地址:" + busiShippingorder.getShipOrderUnloadaddress()+ "\n");
            sb.append("(提货)联系人:" + busiShippingorder.getShipOrderUnloadcontacts() + "\n");
            sb.append("(提货)联系电话:" + busiShippingorder.getShipOrderUnloadway() + "\n");
            //回有，去没有
            if("1".equals(busiShippingorder.getClassEastandwest())){
                sb.append("(提货)邮箱:" + busiShippingorder.getShipOrderUnloadwayEmail() + "\n");
            }
            String thtime = "";
            if(null != busiShippingorder.getShipOrderUnloadtime()){
                thtime = dateFormat.format(busiShippingorder.getShipOrderUnloadtime());
            }
            sb.append("提货时间:" +thtime + "\n");
        }
        String isexamline = busiShippingorder.getIsExamline();
        if(null != isexamline && isexamline !=""){
            isexamline = "0".equals(isexamline)?"待审核":"草稿";
            sb.append("状态："+isexamline);
        }
        return sb.toString();
    }
}
