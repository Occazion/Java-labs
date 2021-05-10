package com.nure.yehor.service.impl;

import com.nure.yehor.entity.Configuration;
import com.nure.yehor.exception.ApplicationException;
import com.nure.yehor.repository.ConfigurationRepository;
import com.nure.yehor.service.ConfigurationService;
import com.nure.yehor.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConnectionService connectionService;
    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationServiceImpl(ConnectionService connectionService, ConfigurationRepository configurationRepository) {
        this.connectionService = connectionService;
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Configuration save(Configuration component) {
        try (Connection connection = connectionService.getConnection()) {
            return configurationRepository.save(component, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public Configuration read(int id) {
        try (Connection connection = connectionService.getConnection()) {
            return configurationRepository.read(id, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Configuration> readAll() {
        try (Connection connection = connectionService.getConnection()) {
            return configurationRepository.readAll(connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Configuration> readPage(int limit, int page) {
        try (Connection connection = connectionService.getConnection()) {
            return configurationRepository.readPage(limit, page, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void update(Configuration configuration) {
        try (Connection connection = connectionService.getConnection()) {
            configurationRepository.update(configuration, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void delete(Configuration configuration) {
        try (Connection connection = connectionService.getConnection()) {
            configurationRepository.delete(configuration, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }
}
