package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订舱询价-拼箱货物详情表
 * </p>
 *
 * @author shahy123
 * @since 2020-01-19
 */
@TableName("booking_inquiry_goods_details")
public class BookingInquiryGoodsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 询价id
     */
    @TableField("inquiry_id")
    private Integer inquiryId;
    /**
     * 货物品名
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 货物数量
     */
    @TableField("goods_amount")
    private String goodsAmount;
    /**
     * 货物重量
     */
    @TableField("goods_weight")
    private String goodsWeight;
    /**
     * 长(mm)
     */
    @TableField("goods_length")
    private String goodsLength;
    /**
     * 宽(mm)
     */
    @TableField("goods_width")
    private String goodsWidth;
    /**
     * 高(mm)
     */
    @TableField("goods_height")
    private String goodsHeight;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(String goodsLength) {
        this.goodsLength = goodsLength;
    }

    public String getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(String goodsWidth) {
        this.goodsWidth = goodsWidth;
    }

    public String getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(String goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    @Override
    public String toString() {
        return "BookingInquiryGoodsDetails{" +
        "id=" + id +
        ", inquiryId=" + inquiryId +
        ", goodsName=" + goodsName +
        ", goodsAmount=" + goodsAmount +
        ", goodsWeight=" + goodsWeight +
        ", goodsLength=" + goodsLength +
        ", goodsWidth=" + goodsWidth +
        ", goodsHeight=" + goodsHeight +
        "}";
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
