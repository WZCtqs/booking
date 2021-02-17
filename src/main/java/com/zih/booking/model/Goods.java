package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 货物信息表
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id",type= IdType.UUID)
    private String goodsId;
    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 唛头
     */
    @TableField("goods_mark")
    private String goodsMark;
    /**
     * 货品中文名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 货品英文名称
     */
    @TableField("goods_en_name")
    private String goodsEnName;
    /**
     * 去回程 0为去程 1为回程
     */
    @TableField("go_back")
    private String goBack;
    /**
     * 国外报关HS
     */
    @TableField("goods_out_report")
    private String goodsOutReport;
    /**
     * 国外清关HS
     */
    @TableField("goods_out_clearance")
    private String goodsOutClearance;
    /**
     * 国内报关
     */
    @TableField("goods_in_report")
    private String goodsInReport;
    /**
     * 国内清关
     */
    @TableField("goods_in_clearance")
    private String goodsInClearance;
    /**
     * 排序字段
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 删除标识 0未删除 1已删除
     */
    @TableField("del_flag")
    private String delFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private Date updateTime;


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getGoBack() {
        return goBack;
    }

    public void setGoBack(String goBack) {
        this.goBack = goBack;
    }

    public String getGoodsOutReport() {
        return goodsOutReport;
    }

    public void setGoodsOutReport(String goodsOutReport) {
        this.goodsOutReport = goodsOutReport;
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

    public String getGoodsInClearance() {
        return goodsInClearance;
    }

    public void setGoodsInClearance(String goodsInClearance) {
        this.goodsInClearance = goodsInClearance;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "goodsId=" + goodsId +
        ", clientId=" + clientId +
        ", goodsMark=" + goodsMark +
        ", goodsName=" + goodsName +
        ", goodsEnName=" + goodsEnName +
        ", goBack=" + goBack +
        ", goodsOutReport=" + goodsOutReport +
        ", goodsOutClearance=" + goodsOutClearance +
        ", goodsInReport=" + goodsInReport +
        ", goodsInClearance=" + goodsInClearance +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
