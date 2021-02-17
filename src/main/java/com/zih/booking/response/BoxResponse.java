package com.zih.booking.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxResponse {
    private String id;
    private String name;
    private String cost;

    @Override
    public boolean equals(Object obj) {
        BoxResponse s=(BoxResponse)obj;
        return name.equals(s.name);
    }

    @Override
    public int hashCode() {
        String in =  name;
        return in.hashCode();
    }
}
