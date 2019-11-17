package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.order.OrderDish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDishBuilder implements EntityBuilder<OrderDish> {

    @Override
    public OrderDish build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(OrderDish.ID_COLUMN);
        int orderID = resultSet.getInt(OrderDish.ORDER_ID_COLUMN);
        int dishID = resultSet.getInt(OrderDish.DISH_ID_COLUMN);
        int servingsNumber = resultSet.getInt(OrderDish.SERVINGS_NUMBER_COLUMN);

        return new OrderDish(ID, orderID, dishID, servingsNumber);
    }
}
