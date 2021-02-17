package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订舱城市表
 * </p>
 *
 * @author shahy123
 * @since 2020-01-11
 */
@TableName("booking_city")
public class BookingCity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 世界城市代码
     */
    @TableField("three_code")
    private String threeCode;
    /**
     * 城市中文名
     */
    @TableField("name_cn")
    private String nameCn;
    /**
     * 城市英文名
     */
    @TableField("name_en")
    private String nameEn;
    /**
     * 所在国家中文名
     */
    @TableField("country_cn")
    private String countryCn;
    /**
     * 所在国家英文名
     */
    @TableField("country_en")
    private String countryEn;
    /**
     * 城市代码
     */
    private String code;
    /**
     * 国家代码
     */
    @TableField("country_code")
    private String countryCode;
    /**
     * 国内国外1-国内 0-国外
     */
    private Integer intra;
    /**
     * 简称
     */
    private String abbr;
    /**
     * 公路部用：线路划分
     */
    @TableField("area_type")
    private Integer areaType;
    /**
     * 是否偏僻点：1-是；0-否
     */
    @TableField("is_remotely")
    private Integer isRemotely;
    /**
     * 1表示多箱区 2表示平衡区 3表示缺箱区
     */
    @TableField("container_number")
    private Integer containerNumber;
    /**
     * 国家代码
     */
    @TableField("line_type")
    private String lineType;


    public String getThreeCode() {
        return threeCode;
    }

    public void setThreeCode(String threeCode) {
        this.threeCode = threeCode;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCountryCn() {
        return countryCn;
    }

    public void setCountryCn(String countryCn) {
        this.countryCn = countryCn;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getIntra() {
        return intra;
    }

    public void setIntra(Integer intra) {
        this.intra = intra;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getIsRemotely() {
        return isRemotely;
    }

    public void setIsRemotely(Integer isRemotely) {
        this.isRemotely = isRemotely;
    }

    public Integer getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(Integer containerNumber) {
        this.containerNumber = containerNumber;
    }

    @Override
    public String toString() {
        return "BookingCity{" +
        "threeCode=" + threeCode +
        ", nameCn=" + nameCn +
        ", nameEn=" + nameEn +
        ", countryCn=" + countryCn +
        ", countryEn=" + countryEn +
        ", code=" + code +
        ", countryCode=" + countryCode +
        ", intra=" + intra +
        ", abbr=" + abbr +
        ", areaType=" + areaType +
        ", isRemotely=" + isRemotely +
        ", containerNumber=" + containerNumber +
        ", lineType=" + lineType +
        "}";
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }
}
