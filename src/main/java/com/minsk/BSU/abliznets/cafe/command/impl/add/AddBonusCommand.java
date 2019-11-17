package com.minsk.BSU.abliznets.cafe.command.impl.add;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.BonusService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.service.impl.BonusServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddBonusCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonuses_table_admin.jsp";

    private HttpServletRequest request;

    public AddBonusCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String bonusName = request.getParameter("bonusName");
        String bonusDescription = request.getParameter("bonusDescription");

        HttpSession session = request.getSession();
        User client = (User) session.getAttribute("client");

        BonusService service = new BonusServiceImpl();
        service.addBonus(new Bonus(bonusName, bonusDescription, client.getID()));

        addBonusToRequest(request, service.getLastBonus());

        return PAGE;
    }
}
