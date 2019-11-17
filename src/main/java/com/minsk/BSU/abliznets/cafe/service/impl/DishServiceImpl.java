package com.minsk.BSU.abliznets.cafe.service.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryFactory;

import com.minsk.BSU.abliznets.cafe.service.ServiceException;
import com.minsk.BSU.abliznets.cafe.repository.specification.dish.*;

import java.util.List;

public class DishServiceImpl implements DishService {
    private static final int FIRST = 0;

    @Override
    public List<Dish> getDishesInMenu() throws ServiceException {
        List<Dish> dishesInMenu;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishesInMenu = dishRepository.query(new DishByInMenuSpecification(true));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting dishes in menu error.", e);
        }

        return dishesInMenu;
    }

    @Override
    public List<Dish> getDishesByIds(List<Integer> dishesIDs) throws ServiceException {
        List<Dish> dishes;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishes = dishRepository.query(new DishByIDsSpecification(dishesIDs));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting dishes by ids error.", e);
        }

        return dishes;
    }

    @Override
    public List<Dish> getAllDishes() throws ServiceException {
        List<Dish> allDishes;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            allDishes = dishRepository.query(new AllDishesSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException("Getting all dishes error.", e);
        }

        return allDishes;
    }

    @Override
    public void updateDish(Dish dish) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishRepository.update(dish);
        } catch (RepositoryException e) {
            throw new ServiceException("Updating dish error.", e);
        }
    }

    @Override
    public void deleteDish(Dish dish) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishRepository.remove(dish);
        } catch (RepositoryException e) {
            throw new ServiceException("Deleting dish error.", e);
        }
    }

    @Override
    public void addDish(Dish dish) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishRepository.save(dish);
        } catch (RepositoryException e) {
            throw new ServiceException("Adding dish error.", e);
        }
    }

    @Override
    public Dish getLastDish() throws ServiceException {
        Dish lastDish;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            List<Dish> dishes = dishRepository.query(new LastDishSpecification());
            lastDish = dishes.get(FIRST);
        } catch (RepositoryException e) {
            throw new ServiceException("Getting last dish error.", e);
        }

        return lastDish;
    }

    @Override
    public List<Dish> getDishesInMenu(int skippingPagesNumber, int recordsCount) throws ServiceException {
        List<Dish> dishesInMenu;

        int skipRecordsCount = skippingPagesNumber * recordsCount;
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishesInMenu = dishRepository.query(
                    new DishByInMenuWithLimitSpecification(true, skipRecordsCount, recordsCount)
            );
        } catch (RepositoryException e) {
            throw new ServiceException("Getting dishes in menu error.", e);
        }

        return dishesInMenu;
    }

    @Override
    public List<Dish> getAllDishes(int skippingPagesNumber, int recordsCount) throws ServiceException {
        List<Dish> allDishes;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            allDishes = dishRepository.query(new AllDishesWithLimitSpecification(
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting all dishes error.", e);
        }

        return allDishes;
    }
}
