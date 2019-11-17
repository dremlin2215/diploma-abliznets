package com.minsk.BSU.abliznets.cafe.api.query;

import java.util.List;

public interface QueryBuilderWithParams {
    String build(String tableName, List<String> params);
}
