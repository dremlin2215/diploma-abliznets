package com.minsk.BSU.abliznets.cafe.repository.specification.user;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;

import java.util.Arrays;
import java.util.List;

public class UserByLoginAndPasswordSpecification implements EntitySpecification<User>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM user WHERE Login = ? AND Password = md5(?)";

    private String desiredLogin;
    private String desiredPassword;

    public UserByLoginAndPasswordSpecification(String desiredLogin, String desiredPassword) {
        this.desiredLogin = desiredLogin;
        this.desiredPassword = desiredPassword;
    }

    @Override
    public boolean specified(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        return (desiredLogin.equals(login) && desiredPassword.equals(password));
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(desiredLogin, desiredPassword);
    }
}
