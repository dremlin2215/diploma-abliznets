package com.minsk.BSU.abliznets.cafe.command.impl.update;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.OrderService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.service.impl.OrderServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RateOrderCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/previous_orders.jsp";

    private HttpServletRequest request;

    public RateOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Integer score = Integer.valueOf(request.getParameter("rating"));

        Order order = findOrder(request);
        order.setScore(score);

        OrderService service = new OrderServiceImpl();
        service.updateOrder(order);

        return PAGE;
    }
}
