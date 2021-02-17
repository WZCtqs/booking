package com.zih.booking.vo;

import lombok.Data;

@Data
public class ProblemFileVo {
    private String url;
    private String fileName;

    @Override
    public boolean equals(Object obj) {
        ProblemFileVo u = (ProblemFileVo) obj;
        return fileName.equals(u.fileName);
    }

    @Override
    public int hashCode() {
        String in = fileName;
        return in.hashCode();
    }
}
