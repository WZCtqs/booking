package com.zih.booking.vo;

import lombok.Data;

@Data
public class ProblemFileVo2 {
    private String url;
    private String name;

    @Override
    public boolean equals(Object obj) {
        ProblemFileVo2 u = (ProblemFileVo2) obj;
        return name.equals(u.name);
    }

    @Override
    public int hashCode() {
        String in = name;
        return in.hashCode();
    }
}
