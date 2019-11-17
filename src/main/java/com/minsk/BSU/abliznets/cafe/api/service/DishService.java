package com.minsk.BSU.abliznets.cafe.api.service;

import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import java.util.List;

public interface DishService {
    List<Dish> getDishesInMenu() throws ServiceException;

    List<Dish> getDishesByIds(List<Integer> dishesIDs) throws ServiceException;

    List<Dish> getAllDishes() throws ServiceException;

    void updateDish(Dish dish) throws ServiceException;

    void deleteDish(Dish dish) throws ServiceException;

    void addDish(Dish dish) throws ServiceException;

    Dish getLastDish() throws ServiceException;

    List<Dish> getDishesInMenu(int pageNumber, int recordsCount) throws ServiceException;

    List<Dish> getAllDishes(int skippingPagesNumber, int recordsCount) throws ServiceException;
}
