package com.minsk.BSU.abliznets.cafe.command.impl.get;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBonusesAdminCommand extends GetBonusesAbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonuses_table_admin.jsp";

    private HttpServletRequest request;

    public GetBonusesAdminCommand(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        HttpSession session = request.getSession();

        User client = findClient(request);
        session.setAttribute("client", client);

        int clientID = Integer.parseInt(request.getParameter("clientID"));

        List<Bonus> bonuses = getBonuses(clientID);
        session.setAttribute("clientBonuses", bonuses);

        return PAGE;
    }
}
