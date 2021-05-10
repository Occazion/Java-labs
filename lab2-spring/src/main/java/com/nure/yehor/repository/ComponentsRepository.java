package com.nure.yehor.repository;

import com.nure.yehor.entity.Component;

import java.sql.Connection;
import java.util.List;

public interface ComponentsRepository {
    Component save(Component component, Connection connection);

    Component read(int id, Connection connection);

    List<Component> readAll(Connection connection);

    List<Component> readPage(int limit, int page, Connection connection);

    void update(Component component, Connection connection);

    void delete(Component component, Connection connection);
}
