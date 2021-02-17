package com.zih.booking.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "关务随车文件数据响应类")
@Data
public class CustomsResponse implements Serializable {

    @ApiModelProperty(value = "班列日期")
    private String classDate;

    @ApiModelProperty(value = "拼箱总票数")
    private Integer lclTotalNum;

    @ApiModelProperty(value = "拼箱已完成票数")
    private Integer lclCompletedNum;


    @ApiModelProperty(value = "整柜总票数")
    private Integer fclTotalNum;

    @ApiModelProperty(value = "整柜已完成票数")
    private Integer fclCompletedNum;

}
