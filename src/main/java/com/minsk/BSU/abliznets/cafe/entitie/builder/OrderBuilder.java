package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.entitie.order.PaymentMethod;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.entitie.order.OrderDish;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.specification.choosen.dishes.ChosenDishesByOrderIDSpecification;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderBuilder implements EntityBuilder<Order> {
    private Repository<OrderDish> chosenDishesRepository;

    public OrderBuilder(Repository<OrderDish> chosenDishesRepository) {
        this.chosenDishesRepository = chosenDishesRepository;
    }

    @Override
    public Order build(ResultSet resultSet) throws SQLException, RepositoryException {
        int ID = resultSet.getInt(Order.ID_COLUMN);

        Timestamp receiptTimestamp = resultSet.getTimestamp(Order.RECEIPT_TIME_COLUMN);
        LocalDateTime receiptTime = receiptTimestamp.toLocalDateTime();

        BigDecimal resultCost = resultSet.getBigDecimal(Order.RESULT_COST_COLUMN);
        PaymentMethod paymentMethod = PaymentMethod.valueOf(resultSet.getString(Order.PAYMENT_METHOD_COLUMN));
        Integer score = resultSet.getInt(Order.SCORE_COLUMN);
        String comment = resultSet.getString(Order.COMMENT_COLUMN);
        int userID = resultSet.getInt(Order.USER_ID_COLUMN);

        Map<Integer, Integer> chosenDishes = new HashMap<>();
        List<OrderDish> orderDishes = chosenDishesRepository.query(new ChosenDishesByOrderIDSpecification(ID));
        for (OrderDish ordersDish : orderDishes) {
            chosenDishes.put(ordersDish.getDishID(), ordersDish.getServingsNumber());
        }

        return new Order(ID, chosenDishes, receiptTime, resultCost, paymentMethod, score, comment, userID);
    }
}
