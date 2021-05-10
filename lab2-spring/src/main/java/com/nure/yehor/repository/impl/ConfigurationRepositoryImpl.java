package com.nure.yehor.repository.impl;

import com.nure.yehor.entity.Configuration;
import com.nure.yehor.exception.ApplicationException;
import com.nure.yehor.mapper.ConfigurationMapper;
import com.nure.yehor.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ConfigurationRepositoryImpl implements ConfigurationRepository {
    private final ConfigurationMapper mapper;
    private final String SQL_READ_BY_ID = "select * from lab2.configuration where id = ?";
    private final String SQL_INSERT_COMPONENT = "INSERT INTO lab2.configuration(name, c_1, c_2, c_3, c_4, c_5) VALUE (?,?,?,?,?,?)";
    private final String SQL_READ_ALL = "select * from lab2.configuration";
    private final String SQL_READ_BY_PAGE = "select * from lab2.configuration limit ? offset ?";
    private final String SQL_UPDATE_COMPONENT = "update lab2.configuration set name = ? , c_1 = ?, c_2 = ?, c_3 = ?, c_4 = ?, c_5 = ? where id = ?";
    private final String SQL_DELETE_BY_ID = "delete from  lab2.configuration where id = ?";
    private final String SQL_READ_BY_NAME = "select * from lab2.configuration where name = ?";

    @Autowired
    public ConfigurationRepositoryImpl(ConfigurationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Configuration save(Configuration configuration, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COMPONENT)) {
            statement.setString(1, configuration.getName());
            statement.setInt(2, configuration.getC1());
            statement.setInt(3, configuration.getC2());
            statement.setInt(4, configuration.getC3());
            statement.setInt(5, configuration.getC4());
            statement.setInt(6, configuration.getC5());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
        }

        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_BY_NAME)) {
            statement.setString(1, configuration.getName());
            return mapper.mapOne(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public Configuration read(int id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_BY_ID)) {
            statement.setInt(1, id);
            return mapper.mapOne(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Configuration> readAll(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_ALL)) {
            return mapper.mapMany(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Configuration> readPage(int limit, int page, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_BY_PAGE)) {
            statement.setInt(1, limit);
            statement.setInt(2, limit * page);
            return mapper.mapMany(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void update(Configuration configuration, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMPONENT)) {
            statement.setString(1, configuration.getName());
            statement.setInt(2, configuration.getC1());
            statement.setInt(2, configuration.getC2());
            statement.setInt(2, configuration.getC3());
            statement.setInt(2, configuration.getC4());
            statement.setInt(2, configuration.getC5());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void delete(Configuration configuration, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(1, configuration.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }
}
