package com.nure.yehor.repository.impl;

import com.nure.yehor.entity.Component;
import com.nure.yehor.exception.ApplicationException;
import com.nure.yehor.mapper.ComponentsMapper;
import com.nure.yehor.repository.ComponentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ComponentsRepositoryImpl implements ComponentsRepository {

    private final ComponentsMapper mapper;
    private final String SQL_READ_BY_ID = "select * from lab2.components where id = ?";
    private final String SQL_INSERT_COMPONENT = "INSERT INTO lab2.components(type,price,name) VALUE (?,?,?)";
    private final String SQL_READ_ALL = "select * from lab2.components";
    private final String SQL_READ_BY_PAGE = "select * from lab2.components limit ? offset ?";
    private final String SQL_UPDATE_COMPONENT = "update lab2.components set type = ? , price = ? ,name = ? where id = ?";
    private final String SQL_DELETE_BY_ID = "delete from  lab2.components where id = ?";
    private final String SQL_READ_BY_NAME = "select * from lab2.components where name = ?";

    @Autowired
    public ComponentsRepositoryImpl(ComponentsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Component save(Component component, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COMPONENT)) {
            statement.setInt(1, component.getType().ordinal() + 1);
            statement.setInt(2, component.getPrice());
            statement.setString(3, component.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
        }

        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_BY_NAME)) {
            statement.setString(1, component.getName());
            return mapper.mapOne(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public Component read(int id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_BY_ID)) {
            statement.setInt(1, id);
            return mapper.mapOne(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Component> readAll(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_READ_ALL)) {
            return mapper.mapMany(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public List<Component> readPage(int limit, int page, Connection connection) {
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
    public void update(Component component, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMPONENT)) {
            statement.setInt(1, component.getType().ordinal());
            statement.setInt(2, component.getPrice());
            statement.setString(3, component.getName());
            statement.setInt(4, component.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }

    @Override
    public void delete(Component component, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(1, component.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error: " + e);
            throw new ApplicationException();
        }
    }
}
