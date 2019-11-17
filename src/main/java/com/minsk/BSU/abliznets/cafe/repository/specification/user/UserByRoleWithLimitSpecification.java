package com.minsk.BSU.abliznets.cafe.repository.specification.user;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;

import java.util.Arrays;
import java.util.List;

public class UserByRoleWithLimitSpecification implements EntitySpecification<User>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM user WHERE Role = ? LIMIT ?,?";

    private UserRole desiredRole;

    private int skipRecordsCount;
    private int recordsCount;

    public UserByRoleWithLimitSpecification(UserRole desiredRole, int skipRecordsCount, int recordsCount) {
        this.desiredRole = desiredRole;
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
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
        return Arrays.asList(desiredRole, skipRecordsCount, recordsCount);
    }
}
