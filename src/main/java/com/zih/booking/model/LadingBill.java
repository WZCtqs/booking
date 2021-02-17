package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2020-03-28
 */
@TableName("lading_bill")
public class LadingBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("lb_id")
    private String lbId;
    @TableField("lb_1")
    private String lb1;
    @TableField("lb_2")
    private String lb2;
    @TableField("lb_3")
    private String lb3;
    @TableField("lb_4")
    private String lb4;
    @TableField("lb_5")
    private String lb5;
    @TableField("lb_6")
    private String lb6;
    @TableField("lb_7")
    private String lb7;
    @TableField("lb_8")
    private String lb8;
    @TableField("lb_9")
    private String lb9;
    @TableField("lb_10")
    private String lb10;
    @TableField("lb_11")
    private String lb11;
    @TableField("lb_12")
    private String lb12;
    @TableField("lb_13_1")
    private String lb131;
    @TableField("lb_13_2")
    private String lb132;
    @TableField("lb_14")
    private String lb14;
    @TableField("lb_15_1")
    private String lb151;
    @TableField("lb_15_2")
    private String lb152;
    @TableField("lb_15_3")
    private String lb153;
    @TableField("lb_16")
    private String lb16;
    @TableField("lb_19")
    private String lb19;
    @TableField("lb_20")
    private String lb20;
    @TableField("lb_remark")
    private String lbRemark;
    @TableField("lb_state")
    private String lbState;
    @TableField("lb_group1")
    private String lbGroup1;
    @TableField("lb_group2")
    private String lbGroup2;
    @TableField("lb_container")
    private String lbContainer;
    @TableField("lb_number")
    private String lbNumber;
    @TableField("lb_mark")
    private String lbMark;
    @TableField("lb_url1")
    private String lbUrl1;
    @TableField("lb_reason1")
    private String lbReason1;
    @TableField("lb_reason2")
    private String lbReason2;
    @TableField("lb_letterstate")
    private String lbLetterState;
    @TableField("ci_id")
    private String ciId;
    @TableField("lb_eastandwest")
    private String lbEastandwest;
    @TableField("order_id")
    private String orderId;
    @TableField("ci_startdate")
    private Date ciStartdate;

    @TableField("lb_client_remark")
    private String lbClientRemark;
    @TableField("update_time")
    private Date updateTime;

    public String getLbClientRemark() {
        return lbClientRemark;
    }

    public void setLbClientRemark(String lbClientRemark) {
        this.lbClientRemark = lbClientRemark;
    }

    public Date getCiStartdate() {
        return ciStartdate;
    }

    public void setCiStartdate(Date ciStartdate) {
        this.ciStartdate = ciStartdate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLbId() {
        return lbId;
    }

    public void setLbId(String lbId) {
        this.lbId = lbId;
    }

    public String getLb1() {
        return lb1;
    }

    public void setLb1(String lb1) {
        this.lb1 = lb1;
    }

    public String getLb2() {
        return lb2;
    }

    public void setLb2(String lb2) {
        this.lb2 = lb2;
    }

    public String getLb3() {
        return lb3;
    }

    public void setLb3(String lb3) {
        this.lb3 = lb3;
    }

    public String getLb4() {
        return lb4;
    }

    public void setLb4(String lb4) {
        this.lb4 = lb4;
    }

    public String getLb5() {
        return lb5;
    }

    public void setLb5(String lb5) {
        this.lb5 = lb5;
    }

    public String getLb6() {
        return lb6;
    }

    public void setLb6(String lb6) {
        this.lb6 = lb6;
    }

    public String getLb7() {
        return lb7;
    }

    public void setLb7(String lb7) {
        this.lb7 = lb7;
    }

    public String getLb8() {
        return lb8;
    }

    public void setLb8(String lb8) {
        this.lb8 = lb8;
    }

    public String getLb9() {
        return lb9;
    }

    public void setLb9(String lb9) {
        this.lb9 = lb9;
    }

    public String getLb10() {
        return lb10;
    }

    public void setLb10(String lb10) {
        this.lb10 = lb10;
    }

    public String getLb11() {
        return lb11;
    }

    public void setLb11(String lb11) {
        this.lb11 = lb11;
    }

    public String getLb12() {
        return lb12;
    }

    public void setLb12(String lb12) {
        this.lb12 = lb12;
    }

    public String getLb131() {
        return lb131;
    }

    public void setLb131(String lb131) {
        this.lb131 = lb131;
    }

    public String getLb132() {
        return lb132;
    }

    public void setLb132(String lb132) {
        this.lb132 = lb132;
    }

    public String getLb14() {
        return lb14;
    }

    public void setLb14(String lb14) {
        this.lb14 = lb14;
    }

    public String getLb151() {
        return lb151;
    }

    public void setLb151(String lb151) {
        this.lb151 = lb151;
    }

    public String getLb152() {
        return lb152;
    }

    public void setLb152(String lb152) {
        this.lb152 = lb152;
    }

    public String getLb153() {
        return lb153;
    }

    public void setLb153(String lb153) {
        this.lb153 = lb153;
    }

    public String getLb16() {
        return lb16;
    }

    public void setLb16(String lb16) {
        this.lb16 = lb16;
    }

    public String getLb19() {
        return lb19;
    }

    public void setLb19(String lb19) {
        this.lb19 = lb19;
    }

    public String getLb20() {
        return lb20;
    }

    public void setLb20(String lb20) {
        this.lb20 = lb20;
    }

    public String getLbRemark() {
        return lbRemark;
    }

    public void setLbRemark(String lbRemark) {
        this.lbRemark = lbRemark;
    }

    public String getLbState() {
        return lbState;
    }

    public void setLbState(String lbState) {
        this.lbState = lbState;
    }

    public String getLbGroup1() {
        return lbGroup1;
    }

    public void setLbGroup1(String lbGroup1) {
        this.lbGroup1 = lbGroup1;
    }

    public String getLbGroup2() {
        return lbGroup2;
    }

    public void setLbGroup2(String lbGroup2) {
        this.lbGroup2 = lbGroup2;
    }

    public String getLbContainer() {
        return lbContainer;
    }

    public void setLbContainer(String lbContainer) {
        this.lbContainer = lbContainer;
    }

    public String getLbNumber() {
        return lbNumber;
    }

    public void setLbNumber(String lbNumber) {
        this.lbNumber = lbNumber;
    }

    public String getLbMark() {
        return lbMark;
    }

    public void setLbMark(String lbMark) {
        this.lbMark = lbMark;
    }

    public String getLbUrl1() {
        return lbUrl1;
    }

    public void setLbUrl1(String lbUrl1) {
        this.lbUrl1 = lbUrl1;
    }

    public String getLbReason1() {
        return lbReason1;
    }

    public void setLbReason1(String lbReason1) {
        this.lbReason1 = lbReason1;
    }

    public String getLbReason2() {
        return lbReason2;
    }

    public void setLbReason2(String lbReason2) {
        this.lbReason2 = lbReason2;
    }

    public String getLbLetterState() {
        return lbLetterState;
    }

    public void setLbLetterState(String lbLetterState) {
        this.lbLetterState = lbLetterState;
    }

    public String getCiId() {
        return ciId;
    }

    public void setCiId(String ciId) {
        this.ciId = ciId;
    }

    public String getLbEastandwest() {
        return lbEastandwest;
    }

    public void setLbEastandwest(String lbEastandwest) {
        this.lbEastandwest = lbEastandwest;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LadingBill{" +
        "lbId=" + lbId +
        ", lb1=" + lb1 +
        ", lb2=" + lb2 +
        ", lb3=" + lb3 +
        ", lb4=" + lb4 +
        ", lb5=" + lb5 +
        ", lb6=" + lb6 +
        ", lb7=" + lb7 +
        ", lb11=" + lb11 +
        ", lb12=" + lb12 +
        ", lb131=" + lb131 +
        ", lb132=" + lb132 +
        ", lb14=" + lb14 +
        ", lb151=" + lb151 +
        ", lb152=" + lb152 +
        ", lb153=" + lb153 +
        ", lb16=" + lb16 +
        ", lb19=" + lb19 +
        ", lb20=" + lb20 +
        ", lbRemark=" + lbRemark +
        ", lbState=" + lbState +
        ", lbGroup1=" + lbGroup1 +
        ", lbGroup2=" + lbGroup2 +
        ", lbContainer=" + lbContainer +
        ", lbNumber=" + lbNumber +
        ", lbMark=" + lbMark +
        ", lbUrl1=" + lbUrl1 +
        ", lbReason1=" + lbReason1 +
        ", lbReason2=" + lbReason2 +
        ", lbLetterState=" + lbLetterState +
        ", ciId=" + ciId +
        ", lbEastandwest=" + lbEastandwest +
        "}";
    }
}
