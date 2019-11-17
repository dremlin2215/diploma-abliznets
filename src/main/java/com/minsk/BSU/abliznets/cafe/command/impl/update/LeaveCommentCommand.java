package com.minsk.BSU.abliznets.cafe.command.impl.update;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.OrderService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.order.Order;
import com.minsk.BSU.abliznets.cafe.service.impl.OrderServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class LeaveCommentCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/previous_orders.jsp";

    private HttpServletRequest request;

    public LeaveCommentCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String comment = request.getParameter("comment");

        Order order = findOrder(request);
        order.setComment(comment);

        OrderService service = new OrderServiceImpl();
        service.updateOrder(order);

        return PAGE;
    }
}
