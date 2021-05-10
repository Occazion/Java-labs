package com.nure.yehor.config;

import com.nure.yehor.mapper.ComponentsMapper;
import com.nure.yehor.mapper.ConfigurationMapper;
import com.nure.yehor.service.ComponentsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ComponentsMapper componentsMapper() {
        return new ComponentsMapper();
    }

    @Bean
    public ConfigurationMapper configurationMapper(ComponentsService componentsService) {
        return new ConfigurationMapper(componentsService);
    }
}
