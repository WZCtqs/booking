package com.zih.booking.vo;

import lombok.Data;

@Data
public class BgFileVo {
    /// 数据操作类型：update,delete,insert
    private String type ;

    private String fileName ;

    /// 订单id

    private String orderId ;

    /// 订单编号
    
    private String orderNumber ;

    /// 箱号

    private String    containerNo ;

    /// 文件url

    private String  fileUrl ;
}
