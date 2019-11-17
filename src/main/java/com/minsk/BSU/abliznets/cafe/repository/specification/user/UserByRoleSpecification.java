package com.minsk.BSU.abliznets.cafe.repository.specification.user;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;

import java.util.Collections;
import java.util.List;

public class UserByRoleSpecification implements EntitySpecification<User>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM user WHERE Role = ?";

    private UserRole desiredRole;

    public UserByRoleSpecification(UserRole desiredRole) {
        this.desiredRole = desiredRole;
    }

    @Override
    public boolean specified(User specifiedElement) {
        UserRole role = specifiedElement.getRole();
        return desiredRole.equals(role);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(desiredRole.name());
    }
}
