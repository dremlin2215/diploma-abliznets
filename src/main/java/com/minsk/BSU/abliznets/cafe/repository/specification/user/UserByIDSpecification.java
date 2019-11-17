package com.minsk.BSU.abliznets.cafe.repository.specification.user;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;

import java.util.Collections;
import java.util.List;

public class UserByIDSpecification implements SqlSpecification, EntitySpecification<User> {
    private static final String QUERY = "SELECT * FROM user WHERE ID = ?";

    private int id;

    public UserByIDSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specified(User specifiedElement) {
        return (specifiedElement.getID() == id);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(id);
    }
}
