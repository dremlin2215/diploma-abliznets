package com.minsk.BSU.abliznets.cafe.command.impl.get;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.DishService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;
import com.minsk.BSU.abliznets.cafe.service.impl.DishServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetClientMenuCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/client_menu.jsp";

    private HttpServletRequest request;

    public GetClientMenuCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        DishService service = new DishServiceImpl();
        HttpSession session = request.getSession();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Dish> dishesInMenu;
        if (pageNumber == 1) {
            dishesInMenu = service.getDishesInMenu();
            int dishesCount = dishesInMenu.size();

            if (dishesCount > recordsCount) {
                dishesInMenu = dishesInMenu.subList(0, recordsCount);
            }

            findPageCount(session, dishesCount, recordsCount);
        } else {
            int skippingPagesNumber = pageNumber - 1;
            dishesInMenu = service.getDishesInMenu(skippingPagesNumber, recordsCount);

        }

        session.setAttribute("dishesInMenu", dishesInMenu);

        return PAGE;
    }
}
