package com.minsk.BSU.abliznets.cafe.service.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.service.OrderService;
import com.minsk.BSU.abliznets.cafe.entitie.Account;
import com.minsk.BSU.abliznets.cafe.entitie.order.PaymentMethod;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryFactory;
import com.minsk.BSU.abliznets.cafe.repository.specification.account.AccountByIDSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.user.UserByIDSpecification;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;
import com.minsk.BSU.abliznets.cafe.repository.specification.order.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final int FIRST = 0;
    private static final PaymentMethod CREDIT_CARD = PaymentMethod.CREDIT_CARD;

    @Override
    public void orderSelected(Map<Integer, Integer> chosenDishes, LocalDateTime dateTime, User currentUser,
                              BigDecimal resultCost, PaymentMethod paymentMethod) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            factory.startTransaction();

            if (CREDIT_CARD.equals(paymentMethod)) {
                withdrawMoney(factory, currentUser, resultCost);
            }

            saveOrder(factory, new Order(
                    chosenDishes, dateTime, resultCost, paymentMethod, currentUser.getID()
            ));

            factory.finishTransaction();
        } catch (RepositoryException e) {
            throw new ServiceException("Ordering selected dishes error.", e);
        }
    }

    @Override
    public List<Order> getPreviousOrders(int userID) throws ServiceException {
        List<Order> previousOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            previousOrders = repository.query(new PreviousOrderByUserIDSpecification(userID));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting previous orders error.", e);
        }

        return previousOrders;
    }

    @Override
    public void updateOrder(Order order) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            repository.update(order);
        } catch (RepositoryException e) {
            throw new ServiceException("Updating order error.", e);
        }
    }

    @Override
    public List<Order> getGlobalOrders(int userID) throws ServiceException {
        List<Order> globalOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            globalOrders = repository.query(new GlobalOrderByUserIDSpecification(userID));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting global orders error.", e);
        }

        return globalOrders;
    }

    @Override
    public List<Order> getCurrentOrders(int userID) throws ServiceException {
        List<Order> currentOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            currentOrders = repository.query(new CurrentOrderByUserIDSpecification(userID));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting current orders error.", e);
        }

        return currentOrders;
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            factory.startTransaction();

            Repository<Order> orderRepository = factory.orderRepository();
            orderRepository.remove(order);

            if (CREDIT_CARD.equals(order.getPaymentMethod())) {
                returnOrderCostToAccount(factory, order);
            }

            factory.finishTransaction();
        } catch (RepositoryException e) {
            throw new ServiceException("Deleting order error.", e);
        }
    }

    @Override
    public List<Order> getCurrentOrders(int userID, int skippingPagesNumber, int recordsCount)
            throws ServiceException {
        List<Order> currentOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            currentOrders = repository.query(new CurrentOrderByUserIDWithLimitSpecification(
                    userID,
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting current orders error.", e);
        }

        return currentOrders;
    }

    @Override
    public List<Order> getGlobalOrders(int userID, int skippingPagesNumber, int recordsCount)
            throws ServiceException {
        List<Order> globalOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            globalOrders = repository.query(new GlobalOrderByUserIDWithLimitSpecification(
                    userID,
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting global orders error.", e);
        }

        return globalOrders;
    }

    @Override
    public List<Order> getPreviousOrders(int userID, int skippingPagesNumber, int recordsCount)
            throws ServiceException {
        List<Order> previousOrders;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Order> repository = factory.orderRepository();
            previousOrders = repository.query(new PreviousOrderByUserIDWithLimitSpecification(
                    userID,
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting previous orders error.", e);
        }

        return previousOrders;
    }

    private void returnOrderCostToAccount(RepositoryFactory factory, Order order)
            throws RepositoryException {
        Repository<User> userRepository = factory.userRepository();
        List<User> users = userRepository.query(new UserByIDSpecification(order.getUserID()));
        User user = users.get(FIRST);

        Repository<Account> accountRepository = factory.accountRepository();
        List<Account> accounts = accountRepository.query(
                new AccountByIDSpecification(user.getAccountID())
        );
        Account account = accounts.get(FIRST);

        BigDecimal oldMoney = account.getMoney();
        BigDecimal returnMoney = order.getResultCost();
        BigDecimal newMoney = oldMoney.add(returnMoney);

        account.setMoney(newMoney);

        accountRepository.update(account);
    }

    private void withdrawMoney(RepositoryFactory factory, User currentUser, BigDecimal resultCost)
            throws RepositoryException, ServiceException {
        Repository<Account> accountRepository = factory.accountRepository();
        List<Account> accounts = accountRepository.query(
                new AccountByIDSpecification(currentUser.getAccountID())
        );

        Account account = accounts.get(FIRST);
        BigDecimal accountMoney = account.getMoney();

        boolean isEnoughMoney = accountMoney.compareTo(resultCost) >= 0;
        if (!isEnoughMoney) {
            throw new ServiceException("Ordering selected dishes error. There is no enough money at account.");
        }

        account.setMoney(accountMoney.subtract(resultCost));
        accountRepository.update(account);
    }

    private void saveOrder(RepositoryFactory factory, Order order) throws RepositoryException {
        Repository<Order> orderRepository = factory.orderRepository();
        orderRepository.save(order);
    }
}
