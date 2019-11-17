package com.minsk.BSU.abliznets.cafe.command.impl.add;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.service.impl.DishServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public AddDishCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String dishName = request.getParameter("dishName");
        BigDecimal dishCost = new BigDecimal(request.getParameter("dishCost"));
        String dishDescription = request.getParameter("dishDescription");
        boolean dishIsInMenu = Boolean.parseBoolean(request.getParameter("dishIsInMenu"));
        String dishImageHref = request.getParameter("dishImageHref");

        DishService service = new DishServiceImpl();
        service.addDish(new Dish(dishName, dishDescription, dishCost, dishImageHref, dishIsInMenu));

        addDishToRequest(request, service.getLastDish());

        return PAGE;
    }
}
