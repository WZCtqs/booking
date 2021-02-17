package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 危险品表
 * </p>
 *
 * @author wsl123
 * @since 2020-07-17
 */
@TableName("busi_shippingorder_dangerous_goods")
public class BusiShippingorderDangerousGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("goods_ID")
    private String goodsId;
    /**
     * 物品名称中文
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 物品名称英文
     */
    @TableField("goods_en_name")
    private String goodsEnName;
    /**
     * 报关hs
     */
    @TableField("goods_report")
    private String goodsReport;
    /**
     * 清关hs
     */
    @TableField("goods_clearance")
    private String goodsClearance;
    /**
     * 特殊备注
     */
    private String specialremark;

    /**
     * 特殊英文备注
     */
    @TableField("special_en_remark")
    private String specialEnRemark;

    /**
     * 等级 0危险品 1疑似危险品
     */
    @TableField("note_level")
    private String noteLevel;
    /**
     * 创建时间
     */
    private Date createdate;


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsEnName(String goodsEnName) {
        this.goodsEnName = goodsEnName;
    }
    public String getGoodsEnName() {
        return goodsEnName;
    }

    public void setGoodsReport(String goodsReport) {
        this.goodsReport = goodsReport;
    }
    public String getGoodsReport() {
        return goodsReport;
    }

    public void setGoodsClearance(String goodsClearance) {
        this.goodsClearance = goodsClearance;
    }
    public String getGoodsClearance() {
        return goodsClearance;
    }

    public void setSpecialremark(String specialremark) {
        this.specialremark = specialremark;
    }
    public String getSpecialremark() {
        return specialremark;
    }

    public void setSpecialEnRemark(String specialEnRemark) {
        this.specialEnRemark = specialEnRemark;
    }
    public String getSpecialEnRemark() {
        return specialEnRemark;
    }

    public String getNoteLevel() {
        return noteLevel;
    }

    public void setNoteLevel(String noteLevel) {
        this.noteLevel = noteLevel;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "BusiShippingorderDangerousGoods{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsEnName=" + goodsEnName +
        ", goodsReport=" + goodsReport +
        ", goodsClearance=" + goodsClearance +
        ", specialremark=" + specialremark +
        ", specialEnRemark=" + specialEnRemark +
        ", noteLevel=" + noteLevel +
        ", createdate=" + createdate +
        "}";
    }
}
