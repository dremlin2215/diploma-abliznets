package com.minsk.BSU.abliznets.cafe.repository.specification.order;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.util.Collections;
import java.util.List;

public class CurrentOrderByUserIDSpecification implements SqlSpecification, EntitySpecification<Order> {
    private static final String QUERY = "SELECT * FROM `order` WHERE UserID = ? AND ReceiptTime > NOW()";

    private int userID;

    public CurrentOrderByUserIDSpecification(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean specified(Order specifiedElement) {
        return false;
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
