package com.minsk.BSU.abliznets.cafe.api.util;

import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.util.List;

public interface OrderHelper {
    Order findOrderById(List<Order> orders, int id);
}
