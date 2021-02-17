package com.zih.booking.response;


import lombok.Data;

import java.util.List;


@Data
public class DocNodeResponse  {

    private String id;
    /**
     * 单证类型
     */

    private String fileTypeKey;
    /**
     * 单证类型说明
     */

    private String fileTypeText;

    private String fileFrom;
    /**
     * 提单草案是否已经提交
     */
    private Integer isSubmit;
    /**
     * 单证地址url
     */

    private List<DocFile> files;
    /**
     * 单证类型说明（英文）
     */

    private String fileTypeTextEn;
    /**
     * 客户的话，设置提醒节点
     */

    private String fileNotice;
    /**
     * 单证作用域：0：去程，1：回程，2：去回程
     */

    private Integer activeArea;
    private Integer  isChoose;
    private Integer lbState;
    private Integer lbLetterState;
    private String lbId;

    /**
     * 电放保函是否已上传
     */
    private Integer hasLatters;
    /*草单审核失败原因*/
    private String lbFaildReason;
    /*电放审核失败原因*/
    private String lbLetterFaildReason;
}
