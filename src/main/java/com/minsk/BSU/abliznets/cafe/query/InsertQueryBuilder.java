package com.minsk.BSU.abliznets.cafe.query;

import com.minsk.BSU.abliznets.cafe.api.query.QueryBuilderWithParams;

import java.util.List;

public class InsertQueryBuilder implements QueryBuilderWithParams {

    @Override
    public String build(String tableName, List<String> params) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(tableName);
        builder.append(" (");

        int last = params.size() - 1;
        for (int i = 0; i < last; i++) {
            builder.append(params.get(i)).append(", ");
        }
        builder.append(params.get(last));

        builder.append(") VALUES(");
        for (int i = 0; i < last; i++) {
            builder.append("?, ");
        }
        builder.append("?)");

        return builder.toString();
    }
}
