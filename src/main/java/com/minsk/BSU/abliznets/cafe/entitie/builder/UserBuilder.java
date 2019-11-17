package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBuilder implements EntityBuilder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(User.ID_COLUMN);
        String login = resultSet.getString(User.LOGIN_COLUMN);
        String password = resultSet.getString(User.PASSWORD_COLUMN);
        String name = resultSet.getString(User.NAME_COLUMN);
        String surname = resultSet.getString(User.SURNAME_COLUMN);
        UserRole role = UserRole.valueOf(resultSet.getString(User.ROLE_COLUMN));
        Boolean isBanned = (Boolean) resultSet.getObject(User.IS_BANNED_COLUMN);
        Integer score = (Integer) resultSet.getObject(User.SCORE_COLUMN);
        Integer accountID = (Integer) resultSet.getObject(User.ACCOUNT_ID_COLUMN);

        return new User(ID, login, password, name, surname, role, isBanned, score, accountID);
    }
}
