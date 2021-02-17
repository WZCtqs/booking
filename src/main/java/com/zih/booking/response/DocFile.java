package com.zih.booking.response;

import lombok.Data;

@Data
public class DocFile {
    private Long id;
    private int isConfirm;
    private String confirmRemark;
    private String fileUrl;
    private String fileName;
    private Integer enabChange;
}
