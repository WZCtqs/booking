package com.zih.booking.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentFileRequest extends BaseRequest {
    private Integer limit;
    private Integer page;
    private String orderNumber;
    private String orderId;
    private String orderMerchandiser;
    private List<String> clientIds;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date classDate;
    private Integer eastOrWest;
    private Long khId;
}
