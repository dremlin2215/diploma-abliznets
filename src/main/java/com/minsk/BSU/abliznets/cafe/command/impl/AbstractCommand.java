package com.minsk.BSU.abliznets.cafe.command.impl;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.api.service.UserService;
import com.minsk.BSU.abliznets.cafe.api.util.BonusHelper;
import com.minsk.BSU.abliznets.cafe.api.util.DishHelper;
import com.minsk.BSU.abliznets.cafe.api.util.OrderHelper;
import com.minsk.BSU.abliznets.cafe.api.util.UserHelper;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.service.impl.DishServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;
import com.minsk.BSU.abliznets.cafe.util.BonusHelperImpl;
import com.minsk.BSU.abliznets.cafe.util.DishHelperImpl;
import com.minsk.BSU.abliznets.cafe.util.OrderHelperImpl;
import com.minsk.BSU.abliznets.cafe.util.UserHelperImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand implements Command {
    private static final String ADMIN_PAGE = "/view/page/administrator/admin.jsp";
    private static final String CLIENT_PAGE = "/view/page/client/client.jsp";
    private static final String ANONYMOUS_PAGE = "/view/page/general/authorization.jsp";

    private UserHelper userHelper = new UserHelperImpl();
    private DishHelper dishHelper = new DishHelperImpl();
    private BonusHelper bonusHelper = new BonusHelperImpl();
    private OrderHelper orderHelper = new OrderHelperImpl();

    protected String findPageByRole(UserRole userRole) {
        String page;

        switch (userRole) {
            case CLIENT:
                page = CLIENT_PAGE;
                break;
            case ADMINISTRATOR:
                page = ADMIN_PAGE;
                break;
            case ANONYMOUS:
                page = ANONYMOUS_PAGE;
                break;
            default:
                throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
        }

        return page;
    }

    protected User findClient(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<User> clients = (ArrayList<User>) session.getAttribute("clients");
        Integer clientID = Integer.valueOf(request.getParameter("clientID"));

        return userHelper.findUserByID(clients, clientID);
    }

    protected Bonus findBonus(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        int bonusID = Integer.parseInt(request.getParameter("bonusID"));

        return bonusHelper.findBonusById(bonuses, bonusID);
    }

    protected Dish findDish(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        int bonusID = Integer.parseInt(request.getParameter("dishID"));

        return dishHelper.findDishById(dishes, bonusID);
    }

    protected Order findOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        return orderHelper.findOrderById(orders, orderID);
    }

    protected void addDishToRequest(HttpServletRequest request, Dish dish) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        dishes.add(dish);
    }

    protected void addBonusToRequest(HttpServletRequest request, Bonus bonus) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        bonuses.add(bonus);
    }

    protected void removeBonusFromRequest(HttpServletRequest request, Bonus bonus) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        bonuses.remove(bonus);
    }

    protected void removeDishFromRequest(HttpServletRequest request, Dish dish) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        dishes.remove(dish);
    }

    protected void removeOrderFromRequest(HttpServletRequest request, Order order) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
        orders.remove(order);
    }

    protected void calculateCostsOfDishesInBasket(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("dishes");

        Map<Integer, BigDecimal> generalCosts = dishHelper.calculateGeneralCosts(dishes, order);
        session.setAttribute("generalCosts", generalCosts);

        BigDecimal result = dishHelper.calculateResult(generalCosts);
        session.setAttribute("resultCost", result);
    }

    protected Map<Integer, Dish> getDishesFromOrders(List<Order> orders, DishService service)
            throws ServiceException {
        List<Integer> dishIDs = dishHelper.getDistinctDishesIdsOfOrders(orders);
        List<Dish> dishes = service.getDishesByIds(dishIDs);

        return dishHelper.convertDishesToIdDishMap(dishes);
    }

    protected Map<Integer, User> getUsersFromOrders(List<Order> orders, UserService service)
            throws ServiceException {
        List<Integer> userIDs = userHelper.getDistinctUserIdsOfOrders(orders);
        List<User> users = service.getUsersByIds(userIDs);

        return userHelper.convertUsersToIdUserMap(users);
    }

    protected void setParamsForCurrentUserOrders(HttpSession session, List<Order> orders)
            throws ServiceException {
        session.setAttribute("orders", orders);

        Map<Integer, Dish> idDishMap = getDishesFromOrders(orders, new DishServiceImpl());
        session.setAttribute("idDishMap", idDishMap);
    }

    protected int findRecordsCount(HttpServletRequest request) {
        int recordsCount = Integer.parseInt(request.getParameter("recordsCount"));

        HttpSession session = request.getSession();
        session.setAttribute("recordsCount", recordsCount);

        return recordsCount;
    }

    protected int findPageNumber(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

        HttpSession session = request.getSession();
        session.setAttribute("currentPageNumber", pageNumber);

        return pageNumber;
    }

    protected void findPageCount(HttpSession session, int entityCount, int recordsCount) {
        int pageCount = (int)(Math.ceil((double) entityCount / (double) recordsCount));
        session.setAttribute("pageCount", pageCount);
    }

    protected int findUserID(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return  user.getID();
    }
}
