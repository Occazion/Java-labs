package com.nure.yehor.entity;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class Component {
    int id;
    Type type;
    int price;
    String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Component ");
        sb.append(id).append(". ");
        sb.append(" type=").append(type);
        sb.append(" price=").append(price);
        sb.append(" name='").append(name).append('\'');
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
