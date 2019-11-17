package com.minsk.BSU.abliznets.cafe.repository.specification.bonus;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;

import java.util.Collections;
import java.util.List;

public class BonusByUserIDSpecification implements EntitySpecification<Bonus>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM bonus WHERE UserID = ?";

    private int desiredUserID;

    public BonusByUserIDSpecification(int desiredUserID) {
        this.desiredUserID = desiredUserID;
    }

    @Override
    public boolean specified(Bonus specifiedElement) {
        return (specifiedElement.getUserID() == desiredUserID);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(desiredUserID);
    }
}
