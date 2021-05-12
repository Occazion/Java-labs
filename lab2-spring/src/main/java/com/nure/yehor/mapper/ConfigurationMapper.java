package com.nure.yehor.mapper;

import com.nure.yehor.entity.Component;
import com.nure.yehor.entity.Configuration;
import com.nure.yehor.exception.ApplicationException;
import com.nure.yehor.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigurationMapper implements Mapper<Configuration> {

    private final ComponentsService componentsService;

    @Autowired
    public ConfigurationMapper(ComponentsService componentsService) {
        this.componentsService = componentsService;
    }

    @Override
    public Configuration mapOne(ResultSet rs) {
        try {
            if (rs.next()) {
                Configuration configuration = mapConfiguration(rs);
                configuration = findComponents(configuration);
                return configuration;
            }
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
        return Configuration.builder().build();
    }

    @Override
    public List<Configuration> mapMany(ResultSet rs) {
        List<Configuration> componentList = new ArrayList<>();
        try {
            while (rs.next()) {
                Configuration configuration = mapConfiguration(rs);
                configuration = findComponents(configuration);
                componentList.add(configuration);
            }
            return componentList;
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    private Configuration mapConfiguration(ResultSet rs) throws SQLException {
        return Configuration.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .c1(rs.getInt("c_1"))
                .c2(rs.getInt("c_2"))
                .c3(rs.getInt("c_3"))
                .c4(rs.getInt("c_4"))
                .c5(rs.getInt("c_5"))
                .build();
    }

    private Configuration findComponents(Configuration configuration) {
        List<Component> componentList = new ArrayList<>();
        componentList.add(componentsService.read(configuration.getC1()));
        componentList.add(componentsService.read(configuration.getC2()));
        componentList.add(componentsService.read(configuration.getC3()));
        componentList.add(componentsService.read(configuration.getC4()));
        componentList.add(componentsService.read(configuration.getC5()));
        componentList = componentList.stream().filter(c -> c.getId() != 0).collect(Collectors.toList());
        return new Configuration(configuration.getId(),
                configuration.getName(),
                configuration.getC1(),
                configuration.getC2(),
                configuration.getC3(),
                configuration.getC4(),
                configuration.getC5(),
                componentList
        );
    }
}
