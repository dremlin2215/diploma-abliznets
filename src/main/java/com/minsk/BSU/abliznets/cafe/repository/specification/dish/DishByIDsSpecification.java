package com.minsk.BSU.abliznets.cafe.repository.specification.dish;

import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilderWithElementsNumber;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.query.SelectIDInQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DishByIDsSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String TABLE_NAME = "dish";

    private List<Integer> dishIDs;

    public DishByIDsSpecification(List<Integer> dishIDs) {
        this.dishIDs = dishIDs;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return dishIDs.contains(specifiedElement.getID());
    }

    @Override
    public String toSqlClause() {
        QueryBuilderWithElementsNumber queryBuilder = new SelectIDInQueryBuilder();
        return queryBuilder.build(TABLE_NAME, dishIDs.size());
    }

    @Override
    public List<Object> getParams() {
        return new ArrayList<>(dishIDs);
    }
}
