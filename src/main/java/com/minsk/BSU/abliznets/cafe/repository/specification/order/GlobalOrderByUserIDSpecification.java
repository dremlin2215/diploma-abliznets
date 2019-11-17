package com.minsk.BSU.abliznets.cafe.repository.specification.order;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.util.Collections;
import java.util.List;

public class GlobalOrderByUserIDSpecification implements SqlSpecification, EntitySpecification<Order> {
    private static final String QUERY = "SELECT * FROM `order` " +
            "WHERE UserID != ? AND ReceiptTime <= NOW() AND (Score IS NOT NULL OR Comment IS NOT NULL)";

    private int userID;

    public GlobalOrderByUserIDSpecification(int userID) {
        this.userID = userID;
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
        return Collections.singletonList(userID);
    }
}
