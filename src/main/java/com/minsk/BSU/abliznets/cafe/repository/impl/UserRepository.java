package com.minsk.BSU.abliznets.cafe.repository.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.builder.UserBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractRepository<User> implements Repository<User> {
    private static final String TABLE_NAME = "user";

    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new UserBuilder());
    }

    @Override
    protected Map<String, Object> getParams(User user) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(User.ID_COLUMN, user.getID());
        params.put(User.LOGIN_COLUMN, user.getLogin());
        params.put(User.PASSWORD_COLUMN, user.getPassword());
        params.put(User.NAME_COLUMN, user.getName());
        params.put(User.SURNAME_COLUMN, user.getSurname());

        UserRole role = user.getRole();
        params.put(User.ROLE_COLUMN, role.name());
        params.put(User.IS_BANNED_COLUMN, user.getBanned());
        params.put(User.SCORE_COLUMN, user.getScore());
        params.put(User.ACCOUNT_ID_COLUMN, user.getAccountID());

        return params;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
