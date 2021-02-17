package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 单证文件表
 * </p>
 *
 * @author wsl123
 * @since 2020-03-27
 */
@TableName("doc_order_document")
public class DocOrderDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 托书编号（舱位号）
     */
    @TableField("order_number")
    private String orderNumber;

    @TableField(exist = false)
    private String classEastAndWest;

    /**
     * 单证类型
     */
    @TableField("file_type_key")
    private String fileTypeKey;
    /**
     * 单证文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 单证地址url
     */
    @TableField("file_url")
    private String fileUrl;
    /**
     * 客户上传提货时间
     */
    @TableField("pick_goods_timec")
    private Date pickGoodsTimec;
    /**
     * 客户提货联系人
     */
    @TableField("pick_goods_contacts")
    private String pickGoodsContacts;
    /**
     * 客户提货详细地址
     */
    @TableField("pick_goods_address")
    private String pickGoodsAddress;
    /**
     * 箱号
     */
    @TableField("container_no")
    private String containerNo;
    /**
     * 箱型
     */
    @TableField("container_type")
    private String containerType;

    private String sealnumber;

    @TableField("container_fail")
    private String containerFail;

    @TableField("container_status")
    private Integer containerStatus;

    //0：待审核， 1：失败，2：成功
    @TableField("boxingphoto_status")
    private Integer boxingphotoStatus;

    @TableField("boxingphoto_fail")
    private String boxingphotoFail;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;
    /**
     * 上传着
     */
    @TableField("upload_by")
    private String uploadBy;
    /**
     * 上传者部门
     */
    @TableField("upload_dept")
    private String uploadDept;
    /**
     * 来源系统
     */
    @TableField("form_system")
    private String formSystem;
    /**
     * 提单草案是否已经提交
     */
    private Integer isSubmit;
    private Integer isChoose;
    private Integer isConfirm;
    private String confirmRemark;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getConfirmRemark() {
        return confirmRemark;
    }

    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }

    public Integer getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(Integer isChoose) {
        this.isChoose = isChoose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getClassEastAndWest() {
        return classEastAndWest;
    }

    public void setClassEastAndWest(String classEastAndWest) {
        this.classEastAndWest = classEastAndWest;
    }

    public String getFileTypeKey() {
        return fileTypeKey;
    }

    public void setFileTypeKey(String fileTypeKey) {
        this.fileTypeKey = fileTypeKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getPickGoodsTimec() {
        return pickGoodsTimec;
    }

    public void setPickGoodsTimec(Date pickGoodsTimec) {
        this.pickGoodsTimec = pickGoodsTimec;
    }

    public String getPickGoodsContacts() {
        return pickGoodsContacts;
    }

    public void setPickGoodsContacts(String pickGoodsContacts) {
        this.pickGoodsContacts = pickGoodsContacts;
    }

    public String getPickGoodsAddress() {
        return pickGoodsAddress;
    }

    public void setPickGoodsAddress(String pickGoodsAddress) {
        this.pickGoodsAddress = pickGoodsAddress;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getSealnumber() {
        return sealnumber;
    }

    public void setSealnumber(String sealnumber) {
        this.sealnumber = sealnumber;
    }

    public String getContainerFail() {
        return containerFail;
    }

    public void setContainerFail(String containerFail) {
        this.containerFail = containerFail;
    }

    public Integer getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(Integer containerStatus) {
        this.containerStatus = containerStatus;
    }

    public Integer getBoxingphotoStatus() {
        return boxingphotoStatus;
    }

    public void setBoxingphotoStatus(Integer boxingphotoStatus) {
        this.boxingphotoStatus = boxingphotoStatus;
    }

    public String getBoxingphotoFail() {
        return boxingphotoFail;
    }

    public void setBoxingphotoFail(String boxingphotoFail) {
        this.boxingphotoFail = boxingphotoFail;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getUploadDept() {
        return uploadDept;
    }

    public void setUploadDept(String uploadDept) {
        this.uploadDept = uploadDept;
    }

    public String getFormSystem() {
        return formSystem;
    }

    public void setFormSystem(String formSystem) {
        this.formSystem = formSystem;
    }

    public Integer getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Integer isSubmit) {
        this.isSubmit = isSubmit;
    }

    @Override
    public String toString() {
        return "DocOrderDocument{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", orderNumber=" + orderNumber +
        ", fileTypeKey=" + fileTypeKey +
        ", fileName=" + fileName +
        ", fileUrl=" + fileUrl +
        ", pickGoodsTimec=" + pickGoodsTimec +
        ", pickGoodsContacts=" + pickGoodsContacts +
        ", pickGoodsAddress=" + pickGoodsAddress +
        ", containerNo=" + containerNo +
        ", containerType=" + containerType +
        ", uploadTime=" + uploadTime +
        ", uploadBy=" + uploadBy +
        ", uploadDept=" + uploadDept +
        ", formSystem=" + formSystem +
        ", isSubmit=" + isSubmit +
        "}";
    }
}
