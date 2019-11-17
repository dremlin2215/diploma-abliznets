package com.minsk.BSU.abliznets.cafe.util;

import com.minsk.BSU.abliznets.cafe.api.util.DishHelper;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;

import java.math.BigDecimal;
import java.util.*;

public class DishHelperImpl implements DishHelper {

    @Override
    public Map<Integer, BigDecimal> calculateGeneralCosts(
            List<Dish> dishes,
            Map<Integer, Integer> order) {
        Map<Integer, BigDecimal> generalCosts = new HashMap<>();

        for (Dish dish : dishes) {
            Integer dishID = dish.getID();
            BigDecimal servingsNumber = BigDecimal.valueOf(order.get(dishID));
            BigDecimal dishCost = dish.getCost();
            BigDecimal generalCost = dishCost.multiply(servingsNumber);

            generalCosts.put(dishID, generalCost);
        }

        return generalCosts;
    }

    @Override
    public BigDecimal calculateResult(Map<Integer, BigDecimal> generalCosts) {
        BigDecimal result = new BigDecimal(0);
        for (BigDecimal generalCost : generalCosts.values()) {
            result = result.add(generalCost);
        }

        return result;
    }

    @Override
    public Dish findDishById(List<Dish> dishes, int dishID) {
        Optional<Dish> foundDish = dishes.stream()
                .filter(dish -> (dish.getID() == dishID))
                .findFirst();

        return foundDish.orElse(null);
    }

    @Override
    public List<Integer> getDistinctDishesIdsOfOrders(List<Order> orders) {
        Set<Integer> dishIDs = new HashSet<>();
        for (Order order : orders) {
            Map<Integer, Integer> chosenDishes = order.getChosenDishes();
            dishIDs.addAll(chosenDishes.keySet());
        }

        return new ArrayList<>(dishIDs);
    }

    @Override
    public Map<Integer, Dish> convertDishesToIdDishMap(List<Dish> dishes) {
        Map<Integer, Dish> idDishMap = new HashMap<>();
        for (Dish dish : dishes) {
            idDishMap.put(dish.getID(), dish);
        }

        return idDishMap;
    }
}
