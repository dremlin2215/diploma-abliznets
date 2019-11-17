package com.minsk.BSU.abliznets.cafe.repository.specification.user;

import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilderWithElementsNumber;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.query.SelectIDInQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserByIDsSpecification implements SqlSpecification, EntitySpecification<User> {
    private static final String TABLE_NAME = "user";

    private List<Integer> userIDs;

    public UserByIDsSpecification(List<Integer> userIDs) {
        this.userIDs = userIDs;
    }

    @Override
    public boolean specified(User specifiedElement) {
        return userIDs.contains(specifiedElement.getID());
    }

    @Override
    public String toSqlClause() {
        QueryBuilderWithElementsNumber queryBuilder = new SelectIDInQueryBuilder();
        return queryBuilder.build(TABLE_NAME, userIDs.size());
    }

    @Override
    public List<Object> getParams() {
        return new ArrayList<>(userIDs);
    }
}
