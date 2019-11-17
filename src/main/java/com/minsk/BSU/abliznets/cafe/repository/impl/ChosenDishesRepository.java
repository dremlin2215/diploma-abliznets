package com.minsk.BSU.abliznets.cafe.repository.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.builder.OrderDishBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.order.OrderDish;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChosenDishesRepository extends AbstractRepository<OrderDish> {
    private static final String TABLE_NAME = "order_dishes";

    public ChosenDishesRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(OrderDish orderDish) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(OrderDish.ID_COLUMN, orderDish.getID());
        params.put(OrderDish.ORDER_ID_COLUMN, orderDish.getOrderID());
        params.put(OrderDish.DISH_ID_COLUMN, orderDish.getDishID());
        params.put(OrderDish.SERVINGS_NUMBER_COLUMN, orderDish.getServingsNumber());

        return params;
    }

    @Override
    public List<OrderDish> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new OrderDishBuilder());
    }
}
