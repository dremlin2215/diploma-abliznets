package com.minsk.BSU.abliznets.cafe.command.impl.update;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.service.impl.DishServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class SaveDishChangesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public SaveDishChangesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Dish dish = findDish(request);

        String dishName = request.getParameter("dishName");
        dish.setName(dishName);

        BigDecimal dishCost = new BigDecimal(request.getParameter("dishCost"));
        dish.setCost(dishCost);

        String dishDescription = request.getParameter("dishDescription");
        dish.setDescription(dishDescription);

        boolean dishIsInMenu = Boolean.parseBoolean(request.getParameter("dishIsInMenu"));
        dish.setInMenu(dishIsInMenu);

        String dishImageHref = request.getParameter("dishImageHref");
        dish.setImageHref(dishImageHref);

        DishService service = new DishServiceImpl();
        service.updateDish(dish);

        return PAGE;
    }
}
