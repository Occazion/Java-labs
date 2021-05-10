package com.nure.yehor.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionService {
    Connection getConnection() throws SQLException;
}
