package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 线路表
 * </p>
 *
 * @author wsl123
 * @since 2020-07-15
 */
@TableName("busi_linesite")
public class BusiLinesite implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 路线ID ，0中欧 2中亚 3中越 4中俄
     */
    private Integer typeid;
    /**
     * 线路中文名
     */
    @TableField("name_cn")
    private String nameCn;
    /**
     * 线路英文名
     */
    @TableField("name_en")
    private String nameEn;
    /**
     * 始发站编号
     */
    @TableField("class_t_StationOfDeparture_code")
    private String classTStationofdepartureCode;
    /**
     * 口岸编号
     */
    @TableField("class_t_port")
    private String classTPort;
    /**
     * 目的站编号
     */
    @TableField("class_t_StationOfDestination_code")
    private String classTStationofdestinationCode;
    /**
     * 线路排序
     */
    private Integer sort;
    /**
     * 站点选择编号
     */
    @TableField("site_codes")
    private String siteCodes;
    /**
     * 0禁用  1启用
     */
    private String state;
    /**
     * 0往 1返
     */
    @TableField("class_t_EastAndWest")
    private String classTEastandwest;
    /**
     * 运行时间
     */
    @TableField("class_t_TransportTime")
    private Integer classTTransporttime;
    /**
     * 0未删除 1删除
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 修改人
     */
    private String updatename;
    /**
     * 修改时间
     */
    private Date updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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

    public String getClassTStationofdepartureCode() {
        return classTStationofdepartureCode;
    }

    public void setClassTStationofdepartureCode(String classTStationofdepartureCode) {
        this.classTStationofdepartureCode = classTStationofdepartureCode;
    }

    public String getClassTPort() {
        return classTPort;
    }

    public void setClassTPort(String classTPort) {
        this.classTPort = classTPort;
    }

    public String getClassTStationofdestinationCode() {
        return classTStationofdestinationCode;
    }

    public void setClassTStationofdestinationCode(String classTStationofdestinationCode) {
        this.classTStationofdestinationCode = classTStationofdestinationCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String siteCodes) {
        this.siteCodes = siteCodes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClassTEastandwest() {
        return classTEastandwest;
    }

    public void setClassTEastandwest(String classTEastandwest) {
        this.classTEastandwest = classTEastandwest;
    }

    public Integer getClassTTransporttime() {
        return classTTransporttime;
    }

    public void setClassTTransporttime(Integer classTTransporttime) {
        this.classTTransporttime = classTTransporttime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "BusiLinesite{" +
        "id=" + id +
        ", typeid=" + typeid +
        ", nameCn=" + nameCn +
        ", nameEn=" + nameEn +
        ", classTStationofdepartureCode=" + classTStationofdepartureCode +
        ", classTPort=" + classTPort +
        ", classTStationofdestinationCode=" + classTStationofdestinationCode +
        ", sort=" + sort +
        ", siteCodes=" + siteCodes +
        ", state=" + state +
        ", classTEastandwest=" + classTEastandwest +
        ", classTTransporttime=" + classTTransporttime +
        ", delFlag=" + delFlag +
        ", updatename=" + updatename +
        ", updatetime=" + updatetime +
        "}";
    }
}
