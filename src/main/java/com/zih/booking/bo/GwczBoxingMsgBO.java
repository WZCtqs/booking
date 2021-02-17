package com.zih.booking.bo;

import com.zih.booking.vo.ProblemFileVo3;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :
 * @Author : wangzhichao
 * @Date: 2021-02-03 15:35
 */
@Data
public class GwczBoxingMsgBO implements Serializable {
    private String containerNo;
    private String Classnumber;
    private List<ProblemFileVo3> ContainerImgUrl;

}
