package com.nure.yehor.service.impl;

import com.nure.yehor.entity.Component;
import com.nure.yehor.exception.ApplicationException;
import com.nure.yehor.repository.ComponentsRepository;
import com.nure.yehor.service.ComponentsService;
import com.nure.yehor.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ComponentsServiceImpl implements ComponentsService {

    private final ConnectionService connectionService;
    private final ComponentsRepository componentsRepository;

    @Autowired
    public ComponentsServiceImpl(ConnectionService connectionService, ComponentsRepository componentsRepository) {
        this.connectionService = connectionService;
        this.componentsRepository = componentsRepository;
    }


    @Override
    public Component save(Component component) {
        try (Connection connection = connectionService.getConnection()) {
            return componentsRepository.save(component, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public Component read(int id) {
        try (Connection connection = connectionService.getConnection()) {
            return componentsRepository.read(id, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Component> readAll() {
        try (Connection connection = connectionService.getConnection()) {
            return componentsRepository.readAll(connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Component> readPage(int limit, int page) {
        try (Connection connection = connectionService.getConnection()) {
            return componentsRepository.readPage(limit, page, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void update(Component component) {
        try (Connection connection = connectionService.getConnection()) {
            componentsRepository.update(component, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void delete(Component component) {
        try (Connection connection = connectionService.getConnection()) {
            componentsRepository.delete(component, connection);
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }
}
