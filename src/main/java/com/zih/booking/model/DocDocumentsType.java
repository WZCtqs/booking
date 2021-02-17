package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 单证类型表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-27
 */
@TableName("doc_documents_type")
public class DocDocumentsType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 所属订单分类（业务模块）
     */
    @TableField("file_order_stage")
    private Integer fileOrderStage;
    /**
     * 单证类型
     */
    @TableField("file_type_key")
    private String fileTypeKey;
    /**
     * 单证类型说明
     */
    @TableField("file_type_text")
    private String fileTypeText;
    /**
     * 单证类型说明（英文）
     */
    @TableField("file_type_text_en")
    private String fileTypeTextEn;
    /**
     * 单证来源：0：系统，1：客户端、2：内部系统
     */
    @TableField("file_from")
    private Integer fileFrom;
    /**
     * 单证来源:系统名称
     */
    @TableField("file_form_text")
    private String fileFormText;
    /**
     * 客户的话，设置提醒节点
     */
    @TableField("file_notice")
    private Integer fileNotice;
    /**
     * 单证作用域：0：去程，1：回程，2：去回程
     */
    @TableField("active_area")
    private Integer activeArea;
    /**
     * 是否系统设定
     */
    @TableField("is_system")
    private Integer isSystem;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFileOrderStage() {
        return fileOrderStage;
    }

    public void setFileOrderStage(Integer fileOrderStage) {
        this.fileOrderStage = fileOrderStage;
    }

    public String getFileTypeKey() {
        return fileTypeKey;
    }

    public void setFileTypeKey(String fileTypeKey) {
        this.fileTypeKey = fileTypeKey;
    }

    public String getFileTypeText() {
        return fileTypeText;
    }

    public void setFileTypeText(String fileTypeText) {
        this.fileTypeText = fileTypeText;
    }

    public String getFileTypeTextEn() {
        return fileTypeTextEn;
    }

    public void setFileTypeTextEn(String fileTypeTextEn) {
        this.fileTypeTextEn = fileTypeTextEn;
    }

    public Integer getFileFrom() {
        return fileFrom;
    }

    public void setFileFrom(Integer fileFrom) {
        this.fileFrom = fileFrom;
    }

    public String getFileFormText() {
        return fileFormText;
    }

    public void setFileFormText(String fileFormText) {
        this.fileFormText = fileFormText;
    }

    public Integer getFileNotice() {
        return fileNotice;
    }

    public void setFileNotice(Integer fileNotice) {
        this.fileNotice = fileNotice;
    }

    public Integer getActiveArea() {
        return activeArea;
    }

    public void setActiveArea(Integer activeArea) {
        this.activeArea = activeArea;
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DocDocumentsType{" +
        "id=" + id +
        ", fileOrderStage=" + fileOrderStage +
        ", fileTypeKey=" + fileTypeKey +
        ", fileTypeText=" + fileTypeText +
        ", fileTypeTextEn=" + fileTypeTextEn +
        ", fileFrom=" + fileFrom +
        ", fileFormText=" + fileFormText +
        ", fileNotice=" + fileNotice +
        ", activeArea=" + activeArea +
        ", isSystem=" + isSystem +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
