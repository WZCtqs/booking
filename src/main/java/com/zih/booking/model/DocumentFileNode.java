package com.zih.booking.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsl123
 * @since 2020-03-05
 */
@TableName("document_file_node")
public class DocumentFileNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    @TableId("node_id")
    private String nodeId;
    /**
     * 状态 0 未处理 1已处理
     */
    @TableField("node_status")
    private String nodeStatus;
    /**
     * 类型 1上传 2 下载 3确认
     */
    @TableField("node_type")
    private String nodeType;
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
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 委托书编号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 单证理论提供时间
     */
    @TableField("virtal_time")
    private Date virtalTime;
    /**
     * 单证实际提供时间
     */
    @TableField("actual_time")
    private Date actualTime;
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


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getVirtalTime() {
        return virtalTime;
    }

    public void setVirtalTime(Date virtalTime) {
        this.virtalTime = virtalTime;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
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

    @Override
    public String toString() {
        return "DocumentFileNode{" +
        "nodeId=" + nodeId +
        ", nodeStatus=" + nodeStatus +
        ", nodeType=" + nodeType +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", delFlag=" + delFlag +
        ", orderNumber=" + orderNumber +
        ", virtalTime=" + virtalTime +
        ", actualTime=" + actualTime +
        ", fileTypeKey=" + fileTypeKey +
        ", fileTypeText=" + fileTypeText +
        "}";
    }
}
