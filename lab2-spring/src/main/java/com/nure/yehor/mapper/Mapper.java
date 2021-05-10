package com.nure.yehor.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface Mapper<T> {
    T mapOne(ResultSet resultSet);

    List<T> mapMany(ResultSet resultSet);
}
