package com.nure.yehor.config;

import com.nure.yehor.service.ConnectionService;
import com.nure.yehor.service.impl.MySQLConnectionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public ConnectionService connectionService() {
        return new MySQLConnectionServiceImpl();
    }
}
