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

public class GetAllDishesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public GetAllDishesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        DishService service = new DishServiceImpl();
        HttpSession session = request.getSession();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Dish> allDishes;
        if (pageNumber == 1) {
            allDishes = service.getAllDishes();
            int dishesCount = allDishes.size();
            findPageCount(session, dishesCount, recordsCount);

            if (dishesCount > recordsCount) {
                allDishes = allDishes.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            allDishes = service.getAllDishes(skippingPagesNumber, recordsCount);
        }

        session.setAttribute("allDishes", allDishes);

        return PAGE;
    }
}
