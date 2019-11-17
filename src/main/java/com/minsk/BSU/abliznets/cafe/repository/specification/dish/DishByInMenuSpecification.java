package com.minsk.BSU.abliznets.cafe.repository.specification.dish;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;

import java.util.Collections;
import java.util.List;

public class DishByInMenuSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish WHERE IsInMenu = ?";

    private boolean inMenu;

    public DishByInMenuSpecification(boolean inMenu) {
        this.inMenu = inMenu;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return specifiedElement.isInMenu() == inMenu;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(inMenu);
    }
}
