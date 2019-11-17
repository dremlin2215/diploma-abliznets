package com.minsk.BSU.abliznets.cafe.util;

import com.minsk.BSU.abliznets.cafe.api.util.OrderHelper;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.util.List;
import java.util.Optional;

public class OrderHelperImpl implements OrderHelper {

    @Override
    public Order findOrderById(List<Order> orders, int id) {
        Optional<Order> foundOrder = orders.stream()
                .filter(order -> (order.getID() == id))
                .findFirst();

        return foundOrder.orElse(null);
    }
}
