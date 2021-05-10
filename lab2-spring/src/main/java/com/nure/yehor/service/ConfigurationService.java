package com.nure.yehor.service;

import com.nure.yehor.entity.Configuration;

import java.util.List;

public interface ConfigurationService {
    Configuration save(Configuration configuration);

    Configuration read(int id);

    List<Configuration> readAll();

    List<Configuration> readPage(int limit, int page);

    void update(Configuration configuration);

    void delete(Configuration configuration);
}
