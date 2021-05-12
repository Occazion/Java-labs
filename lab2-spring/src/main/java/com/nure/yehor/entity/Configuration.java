package com.nure.yehor.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Configuration {
    int id;
    String name;
    int c1;
    int c2;
    int c3;
    int c4;
    int c5;
    List<Component> componentList;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Configuration ");
        sb.append(id).append(". ");
        sb.append(" name='").append(name).append('\'');
        sb.append(" c1=").append(c1);
        sb.append(" c2=").append(c2);
        sb.append(" c3=").append(c3);
        sb.append(" c4=").append(c4);
        sb.append(" c5=").append(c5);
        if (!componentList.isEmpty()) {
            sb.append(" list:")
                    .append(System.lineSeparator())
                    .append(componentList);
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
