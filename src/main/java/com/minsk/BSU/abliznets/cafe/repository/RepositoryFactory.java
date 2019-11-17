package com.minsk.BSU.abliznets.cafe.repository;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.connection.pool.ConnectionPool;
import com.minsk.BSU.abliznets.cafe.entitie.Account;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.repository.impl.*;

import java.sql.Connection;
import java.sql.SQLException;

public class RepositoryFactory implements AutoCloseable {
    private Connection connection;

    public RepositoryFactory() throws RepositoryException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.retrieveConnection();
        } catch (InterruptedException e) {
            throw new RepositoryException("Getting connection exception.", e);
        }
    }

    public Repository<User> userRepository() {
        return new UserRepository(connection);
    }

    public Repository<Bonus> bonusRepository() {
        return new BonusRepository(connection);
    }

    public Repository<Dish> dishRepository() {
        return new DishRepository(connection);
    }

    public Repository<Order> orderRepository() {
        return new OrderRepository(connection, new ChosenDishesRepository(connection));
    }

    public Repository<Account> accountRepository() {
        return new AccountRepository(connection);
    }

    public void startTransaction() throws RepositoryException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RepositoryException("Starting transaction error.", e);
        }
    }

    public void finishTransaction() throws RepositoryException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RepositoryException("Finishing transaction error.", e);
        }
    }

    @Override
    public void close() throws RepositoryException {
        try {
            boolean transactionIsNotCompleted = !connection.getAutoCommit();
            if (transactionIsNotCompleted) {
                connection.rollback();
                connection.setAutoCommit(true);
            }

            ConnectionPool pool = ConnectionPool.getInstance();
            pool.storeConnection(connection);
        } catch (InterruptedException | SQLException e) {
            throw new RepositoryException("Closing repository error.", e);
        }
    }
}
