package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单—单证（订单所需单证）对象 doc_order
 *
 * @author hp
 * @date 2020-04-13
 */
@Data
@TableName("doc_order")
public class DocOrder implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单id */
    @TableField("order_id")
    private String orderId;

    /** 订单编号 */
    @TableField("order_number")
    private String orderNumber;

    /** 班列日期 */
    @TableField("class_date")
    private Date classDate;

    /** 托书审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("audit_time")
    private Date auditTime;

    /** 所需单证 */
    @TableField("file_type_key")
    private String fileTypeKey;

    /** 所需单证 */
    @TableField("file_type_text")
    private String fileTypeText;

    /** 理论提供时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("normal_supply_node")
    private Date normalSupplyNode;

    @TableField("actual_supply_time")
    private Date actualSupplyTime;

    @TableField("actual_supply")
    private Integer actualSupply;

    @TableField("email_status")
    private Integer emailStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderNumber", getOrderNumber())
            .append("classDate", getClassDate())
            .append("auditTime", getAuditTime())
            .append("fileTypeKey", getFileTypeKey())
            .append("fileTypeText", getFileTypeText())
            .append("normalSupplyNode", getNormalSupplyNode())
            .toString();
    }
}
