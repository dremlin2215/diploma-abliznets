package com.minsk.BSU.abliznets.cafe.repository.impl;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilder;
import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilderWithParams;
import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.query.DeleteQueryBuilder;
import com.minsk.BSU.abliznets.cafe.query.InsertQueryBuilder;
import com.minsk.BSU.abliznets.cafe.query.UpdateQueryBuilder;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements Repository<T> {
    private static final String ID_COLUMN = "ID";
    private static final int ID_COLUMN_INDEX = 1;

    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(T element) throws RepositoryException {
        Map<String, Object> paramsMap = getParams(element);
        paramsMap.remove(ID_COLUMN);

        List<String> params = new ArrayList<>(paramsMap.keySet());
        List<Object> values = new ArrayList<>(paramsMap.values());

        QueryBuilderWithParams queryBuilder = new InsertQueryBuilder();
        try (PreparedStatement statement = makeStatementWithParams(queryBuilder, params, values)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Saving to repository error.", e);
        }
    }

    @Override
    public void update(T element) throws RepositoryException {
        Map<String, Object> paramsMap = getParams(element);
        List<String> params = new ArrayList<>(paramsMap.keySet());
        List<Object> values = new ArrayList<>(paramsMap.values());

        QueryBuilderWithParams queryBuilder = new UpdateQueryBuilder();
        try (PreparedStatement statement = makeStatementWithParams(queryBuilder, params, values)) {
            int whereParamIndex = params.size() + 1;
            Object whereValue = paramsMap.get(ID_COLUMN);
            statement.setObject(whereParamIndex, whereValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Repository updating error.", e);
        }
    }

    private PreparedStatement makeStatementWithParams(QueryBuilderWithParams queryBuilder,
                                                      List<String> params,
                                                      List<Object> values) throws SQLException {
        String query = queryBuilder.build(getTableName(), params);
        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }

        return statement;
    }

    @Override
    public void remove(T element) throws RepositoryException {
        QueryBuilder queryBuilder = new DeleteQueryBuilder();
        String query = queryBuilder.build(getTableName());

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Map<String, Object> paramsMap = getParams(element);
            Object idValue = paramsMap.get(ID_COLUMN);
            statement.setObject(ID_COLUMN_INDEX, idValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Removing from repository error.", e);
        }
    }

    protected List<T> executeQuery(SqlSpecification specification, EntityBuilder<T> builder)
            throws RepositoryException {
        List<T> builtEntities = new ArrayList<>();
        String query = specification.toSqlClause();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            List<Object> params = specification.getParams();
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    T entity = builder.build(resultSet);
                    builtEntities.add(entity);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error when execute searching query.", e);
        }

        return builtEntities;
    }

    protected int getLastID() throws SQLException {
        Statement lastIDStatement = connection.createStatement();
        String query = "SELECT MAX(ID) FROM " + getTableName();
        ResultSet resultSet = lastIDStatement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt(ID_COLUMN_INDEX);
    }

    protected abstract String getTableName();

    protected abstract Map<String, Object> getParams(T entity);
}
