package com.minsk.BSU.abliznets.cafe.api.util;

import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DishHelper {
    Map<Integer, BigDecimal> calculateGeneralCosts(List<Dish> dishes, Map<Integer, Integer> order);

    BigDecimal calculateResult(Map<Integer, BigDecimal> generalCosts);

    Dish findDishById(List<Dish> dishes, int id);

    List<Integer> getDistinctDishesIdsOfOrders(List<Order> orders);

    Map<Integer, Dish> convertDishesToIdDishMap(List<Dish> dishes);
}
