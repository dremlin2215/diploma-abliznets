package com.minsk.BSU.abliznets.cafe.query;

import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilder;

public class DeleteQueryBuilder implements QueryBuilder {

    @Override
    public String build(String tableName) {
        return "DELETE FROM " + tableName + " WHERE ID = ?";
    }
}
