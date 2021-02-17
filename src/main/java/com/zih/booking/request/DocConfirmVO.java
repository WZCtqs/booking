package com.zih.booking.request;

import com.zih.booking.vo.ProblemFileVo;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @Description : 确认单证上传
 * @Author : wangzhichao
 * @Date: 2020-09-24 11:05
 */
@Data
public class DocConfirmVO implements Serializable {
    private String lbId;
    private String fileTypeKey;
    private String orderNumber;
    private String xianghao;
    private String orderId;
    private String confirmRemark;
    private ProblemFileVo[] urls;
}
