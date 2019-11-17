package com.minsk.BSU.abliznets.cafe.query;

import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilderWithParams;

import java.util.List;

public class UpdateQueryBuilder implements QueryBuilderWithParams {

    @Override
    public String build(String tableName, List<String> params) {
        StringBuilder builder = new StringBuilder("UPDATE ");
        builder.append(tableName);
        builder.append(" SET ");

        int last = params.size() - 1;
        for (int i = 0; i < last; i++) {
            builder.append(params.get(i));
            builder.append(" = ?, ");
        }
        builder.append(params.get(last));
        builder.append(" = ? WHERE ID = ?");

        return builder.toString();
    }
}
