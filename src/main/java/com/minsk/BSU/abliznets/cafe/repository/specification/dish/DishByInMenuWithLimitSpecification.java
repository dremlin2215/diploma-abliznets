package com.minsk.BSU.abliznets.cafe.repository.specification.dish;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class DishByInMenuWithLimitSpecification implements SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish WHERE IsInMenu = ? LIMIT ?,?";

    private boolean inMenu;

    private int skipRecordsCount;
    private int recordsCount;

    public DishByInMenuWithLimitSpecification(boolean inMenu, int skipRecordsCount, int recordsCount) {
        this.inMenu = inMenu;
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(inMenu, skipRecordsCount, recordsCount);
    }
}
