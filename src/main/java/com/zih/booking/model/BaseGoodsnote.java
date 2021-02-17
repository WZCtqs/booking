package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 特殊单证物品表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@TableName("base_goodsnote")
public class BaseGoodsnote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 去回程：0西向 1东向  0|1东西向
     */
    private String EastAndWest;
    /**
     * 货物品名
     */
    private String goodsname;
    /**
     * 国内HS
     */
    private String inHS;
    /**
     * 上/下货站
     */
    private String unloadSite;
    /**
     * 口岸
     */
    private String station;
    /**
     * 特殊备注
     */
    private String specialnotes;
    /**
     * 放射性 否/是
     */
    private String radioaction;
    /**
     * 商品类别0是特殊单证1是有色金属
     */
    @TableField("goods_class")
    private String goodsClass;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEastAndWest() {
        return EastAndWest;
    }

    public void setEastAndWest(String EastAndWest) {
        this.EastAndWest = EastAndWest;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getInHS() {
        return inHS;
    }

    public void setInHS(String inHS) {
        this.inHS = inHS;
    }

    public String getUnloadSite() {
        return unloadSite;
    }

    public void setUnloadSite(String unloadSite) {
        this.unloadSite = unloadSite;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getSpecialnotes() {
        return specialnotes;
    }

    public void setSpecialnotes(String specialnotes) {
        this.specialnotes = specialnotes;
    }

    public String getRadioaction() {
        return radioaction;
    }

    public void setRadioaction(String radioaction) {
        this.radioaction = radioaction;
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }

    @Override
    public String toString() {
        return "BaseGoodsnote{" +
        "id=" + id +
        ", EastAndWest=" + EastAndWest +
        ", goodsname=" + goodsname +
        ", inHS=" + inHS +
        ", unloadSite=" + unloadSite +
        ", station=" + station +
        ", specialnotes=" + specialnotes +
        ", radioaction=" + radioaction +
        ", goodsClass=" + goodsClass +
        "}";
    }
}
