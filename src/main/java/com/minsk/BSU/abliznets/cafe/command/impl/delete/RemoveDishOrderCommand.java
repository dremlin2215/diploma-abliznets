package com.minsk.BSU.abliznets.cafe.command.impl.delete;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class RemoveDishOrderCommand extends AbstractCommand implements Command {
    private static final String BASKET_PAGE = "/view/page/client/basket.jsp";
    private static final String EMPTY_BASKET_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public RemoveDishOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");

        Integer selectedDishID = Integer.valueOf(request.getParameter("selectedDishID"));
        order.remove(selectedDishID);

        String page;
        if (order != null && !order.isEmpty()) {
            calculateCostsOfDishesInBasket(session);

            page = BASKET_PAGE;
        } else {
            page = EMPTY_BASKET_PAGE;
        }

        return page;
    }
}
