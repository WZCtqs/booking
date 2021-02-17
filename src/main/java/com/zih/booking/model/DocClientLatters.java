package com.zih.booking.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户维护长期电放保函对象 doc_client_latters
 * 
 * @author szhbl
 * @date 2020-10-01
 */
@Data
@TableName("doc_client_latters")
public class DocClientLatters implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    /** 客户id */
    @TableField("client_id")
    private String clientId;

    /** 电放保函文件名 */
    @TableField("latter_name")
    private String latterName;

    /** 电放保函url连接 */
    @TableField("latter_url")
    private String latterUrl;

    /** 有效开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("active_start_time")
    private Date activeStartTime;

    /** 有效结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("active_end_time")
    private Date activeEndTime;

    /** 删除标志 */
    @TableField("del_flag")
    private Integer delFlag;

    /** 上传时间 */
    @TableField("upload_time")
    private Date uploadTime;

    @Override
    public String toString() {
        return "DocClientLatters{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", latterName='" + latterName + '\'' +
                ", latterUrl='" + latterUrl + '\'' +
                ", delFlag=" + delFlag +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
