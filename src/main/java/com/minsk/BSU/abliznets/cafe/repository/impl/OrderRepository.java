package com.minsk.BSU.abliznets.cafe.repository.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.order.PaymentMethod;
import com.minsk.BSU.abliznets.cafe.entitie.builder.OrderBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.entitie.order.OrderDish;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.specification.choosen.dishes.ChosenDishesByOrderIDSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository extends AbstractRepository<Order> {
    private static final String TABLE_NAME = "`order`";

    private Repository<OrderDish> chosenDishesRepository;

    public OrderRepository(Connection connection, Repository<OrderDish> chosenDishesRepository) {
        super(connection);
        this.chosenDishesRepository = chosenDishesRepository;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(Order order) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Order.ID_COLUMN, order.getID());
        params.put(Order.RECEIPT_TIME_COLUMN, order.getReceiptTime());
        params.put(Order.RESULT_COST_COLUMN, order.getResultCost());

        PaymentMethod paymentMethod = order.getPaymentMethod();
        params.put(Order.PAYMENT_METHOD_COLUMN, paymentMethod.name());
        params.put(Order.SCORE_COLUMN, order.getScore());
        params.put(Order.COMMENT_COLUMN, order.getComment());
        params.put(Order.USER_ID_COLUMN, order.getUserID());

        return params;
    }

    @Override
    public void save(Order order) throws RepositoryException {
        try {
            super.save(order);
            int orderID = getLastID();

            Map<Integer, Integer> chosenDishes = order.getChosenDishes();
            for (Map.Entry<Integer, Integer> entry : chosenDishes.entrySet()) {
                Integer dishID = entry.getKey();
                Integer servingsNumber = entry.getValue();
                chosenDishesRepository.save(new OrderDish(orderID, dishID, servingsNumber));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Saving to order repository error.", e);
        }
    }

    @Override
    public void remove(Order order) throws RepositoryException {
        int orderID = order.getID();
        List<OrderDish> orderDishes = chosenDishesRepository.query(
                new ChosenDishesByOrderIDSpecification(orderID)
        );

        for (OrderDish orderDish : orderDishes) {
            chosenDishesRepository.remove(orderDish);
        }

        super.remove(order);
    }

    @Override
    public List<Order> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new OrderBuilder(chosenDishesRepository));
    }
}
