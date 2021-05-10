package com.nure.yehor.service.impl;

import com.nure.yehor.service.ConnectionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.SneakyThrows;

@Service
public class MySQLConnectionServiceImpl implements ConnectionService {

    @Value("${mysql.user}")
    private String user;
    @Value("${mysql.password}")
    private String pwd;
    @Value("${mysql.url}")
    private String url;

    @SneakyThrows
    public MySQLConnectionServiceImpl() {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pwd);
    }
}
