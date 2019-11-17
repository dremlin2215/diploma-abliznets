package com.minsk.BSU.abliznets.cafe.repository.specification.order;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.util.Arrays;
import java.util.List;

public class GlobalOrderByUserIDWithLimitSpecification implements SqlSpecification, EntitySpecification<Order> {
    private static final String QUERY = "SELECT * FROM `order` " +
            "WHERE UserID != ? AND ReceiptTime <= NOW() AND (Score IS NOT NULL OR Comment IS NOT NULL) " +
            "LIMIT ?,?";

    private int userID;

    private int skipRecordsCount;
    private int recordsCount;

    public GlobalOrderByUserIDWithLimitSpecification(int userID, int skipRecordsCount, int recordsCount) {
        this.userID = userID;
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
    }

    @Override
    public boolean specified(Order specifiedElement) {
        return (specifiedElement.getID() != userID);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(userID, skipRecordsCount, recordsCount);
    }
}
