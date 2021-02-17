package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 省份表
 * </p>
 *
 * @author shahy123
 * @since 2019-12-25
 */
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 省份code
     */
    @TableField("province_code")
    private String provinceCode;
    /**
     * 省份名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 省份名称英文
     */
    @TableField("province_name_en")
    private String provinceNameEn;
    /**
     * 中心坐标
     */
    @TableField("province_center")
    private String provinceCenter;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCenter() {
        return provinceCenter;
    }

    public void setProvinceCenter(String provinceCenter) {
        this.provinceCenter = provinceCenter;
    }

    @Override
    public String toString() {
        return "Province{" +
        "id=" + id +
        ", provinceCode=" + provinceCode +
        ", provinceName=" + provinceName +
        ", provinceCenter=" + provinceCenter +
        "}";
    }

    public String getProvinceNameEn() {
        return provinceNameEn;
    }

    public void setProvinceNameEn(String provinceNameEn) {
        this.provinceNameEn = provinceNameEn;
    }
}
