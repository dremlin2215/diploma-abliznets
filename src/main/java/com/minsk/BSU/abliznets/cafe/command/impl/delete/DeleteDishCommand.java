package com.minsk.BSU.abliznets.cafe.command.impl.delete;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.service.impl.DishServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public DeleteDishCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Dish dish = findDish(request);

        DishService service = new DishServiceImpl();
        service.deleteDish(dish);

        removeDishFromRequest(request, dish);

        return PAGE;
    }
}
