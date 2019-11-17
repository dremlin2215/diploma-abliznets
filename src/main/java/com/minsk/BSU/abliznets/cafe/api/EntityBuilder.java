package com.minsk.BSU.abliznets.cafe.api;

import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityBuilder<T> {
    T build(ResultSet resultSet) throws SQLException, RepositoryException;
}
