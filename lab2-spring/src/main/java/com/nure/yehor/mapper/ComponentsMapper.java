package com.nure.yehor.mapper;

import com.nure.yehor.entity.Component;
import com.nure.yehor.entity.Type;
import com.nure.yehor.exception.ApplicationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComponentsMapper implements Mapper<Component> {
    @Override
    public Component mapOne(ResultSet rs) {
        try {
            if (rs.next()) {
                return mapComponent(rs);
            }
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
        return Component.builder().build();
    }

    @Override
    public List<Component> mapMany(ResultSet rs) {
        List<Component> componentList = new ArrayList<>();
        try {
            while (rs.next()) {
                componentList.add(mapComponent(rs));
            }
            return componentList;
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    private Component mapComponent(ResultSet rs) throws SQLException {
        return Component.builder()
                .id(rs.getInt("id"))
                .type(Type.valueOf(rs.getString("type").toUpperCase()))
                .price(rs.getInt("price"))
                .name(rs.getString("name"))
                .build();
    }
}
