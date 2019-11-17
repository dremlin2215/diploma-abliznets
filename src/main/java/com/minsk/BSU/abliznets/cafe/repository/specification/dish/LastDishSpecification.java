package com.minsk.BSU.abliznets.cafe.repository.specification.dish;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;

import java.util.Collections;
import java.util.List;

public class LastDishSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish WHERE ID = (SELECT MAX(ID) FROM dish)";

    @Override
    public boolean specified(Dish specifiedElement) {
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
