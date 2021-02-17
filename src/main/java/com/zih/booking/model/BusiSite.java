package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 城市站点表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@TableName("busi_site")
public class BusiSite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 国家代码
     */
    @TableField("country_code")
    private String countryCode;
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
     * 时区
     */
    @TableField("time_zone")
    private String timeZone;
    /**
     * 线路类别 0中欧 2中亚 3中越 4中俄
     */
    @TableField("line_typeid")
    private String lineTypeid;
    /**
     * 0禁用  1启用
     */
    private String state;
    /**
     * 用途：站点/城市
     */
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLineTypeid() {
        return lineTypeid;
    }

    public void setLineTypeid(String lineTypeid) {
        this.lineTypeid = lineTypeid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BusiSite{" +
        "id=" + id +
        ", countryCode=" + countryCode +
        ", countryCn=" + countryCn +
        ", countryEn=" + countryEn +
        ", code=" + code +
        ", nameCn=" + nameCn +
        ", nameEn=" + nameEn +
        ", timeZone=" + timeZone +
        ", lineTypeid=" + lineTypeid +
        ", state=" + state +
        ", type=" + type +
        "}";
    }
}
