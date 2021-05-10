package com.nure.yehor.service;

import com.nure.yehor.entity.Component;

import java.util.List;

public interface ComponentsService {
    Component save(Component component);

    Component read(int id);

    List<Component> readAll();

    List<Component> readPage(int limit, int page);

    void update(Component component);

    void delete(Component component);
}
