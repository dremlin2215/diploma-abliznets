package com.minsk.BSU.abliznets.cafe.command.impl.update;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.UserService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.service.impl.UserServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveClientChangesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/user_table.jsp";

    private HttpServletRequest request;

    public SaveClientChangesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        User client = findClient(request);

        Boolean clientIsBanned = Boolean.valueOf(request.getParameter("clientIsBanned"));
        client.setBanned(clientIsBanned);

        Integer clientScore = Integer.valueOf(request.getParameter("clientScore"));
        client.setScore(clientScore);

        UserService service = new UserServiceImpl();
        service.updateUser(client);

        return PAGE;
    }
}
