package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author shahy123
 * @since 2019-12-25
 */
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 区域code
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 区域名称英文
     */
    @TableField("area_name_en")
    private String areaNameEn;

    /**
     * 区域中心坐标
     */
    @TableField("area_center")
    private String areaCenter;
    /**
     * 城市code
     */
    @TableField("city_code")
    private String cityCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCenter() {
        return areaCenter;
    }

    public void setAreaCenter(String areaCenter) {
        this.areaCenter = areaCenter;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "Area{" +
        "id=" + id +
        ", areaCode=" + areaCode +
        ", areaName=" + areaName +
        ", areaCenter=" + areaCenter +
        ", cityCode=" + cityCode +
        "}";
    }

    public String getAreaNameEn() {
        return areaNameEn;
    }

    public void setAreaNameEn(String areaNameEn) {
        this.areaNameEn = areaNameEn;
    }
}
