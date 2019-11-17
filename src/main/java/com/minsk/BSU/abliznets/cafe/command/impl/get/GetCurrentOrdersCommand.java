package com.minsk.BSU.abliznets.cafe.command.impl.get;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.OrderService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.service.impl.OrderServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetCurrentOrdersCommand extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/current_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public GetCurrentOrdersCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        OrderService service = new OrderServiceImpl();
        HttpSession session = request.getSession();

        int userID = findUserID(session);

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Order> orders;
        if (pageNumber == 1) {
            orders = service.getCurrentOrders(userID);
            int ordersCount = orders.size();
            findPageCount(session, ordersCount, recordsCount);

            if (ordersCount > recordsCount) {
                orders = orders.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            orders = service.getCurrentOrders(userID, skippingPagesNumber, recordsCount);
        }

        String page;
        if (!orders.isEmpty()) {
            setParamsForCurrentUserOrders(session, orders);

            page = ORDERS_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
