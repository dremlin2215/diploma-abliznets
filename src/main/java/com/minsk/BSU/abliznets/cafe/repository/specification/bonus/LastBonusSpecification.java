package com.minsk.BSU.abliznets.cafe.repository.specification.bonus;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;

import java.util.Collections;
import java.util.List;

public class LastBonusSpecification implements SqlSpecification, EntitySpecification<Bonus> {
    private static final String QUERY = "SELECT * FROM bonus WHERE ID = (SELECT MAX(ID) FROM bonus)";

    @Override
    public boolean specified(Bonus specifiedElement) {
        return false;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.emptyList();
    }
}
