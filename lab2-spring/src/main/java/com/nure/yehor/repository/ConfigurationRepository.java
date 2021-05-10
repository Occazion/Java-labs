package com.nure.yehor.repository;

import com.nure.yehor.entity.Configuration;

import java.sql.Connection;
import java.util.List;

public interface ConfigurationRepository {

    Configuration save(Configuration configuration, Connection connection);

    Configuration read(int id, Connection connection);

    List<Configuration> readAll(Connection connection);

    List<Configuration> readPage(int limit, int page, Connection connection);

    void update(Configuration configuration, Connection connection);

    void delete(Configuration configuration, Connection connection);

}
