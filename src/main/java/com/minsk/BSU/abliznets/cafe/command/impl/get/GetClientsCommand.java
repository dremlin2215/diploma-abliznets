package com.minsk.BSU.abliznets.cafe.command.impl.get;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.UserService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.service.impl.UserServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetClientsCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/user_table.jsp";

    private HttpServletRequest request;

    public GetClientsCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        UserService service = new UserServiceImpl();
        HttpSession session = request.getSession();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<User> clients;
        if (pageNumber == 1) {
            clients = service.getClients();
            int clientsCount = clients.size();
            findPageCount(session, clientsCount, recordsCount);

            if (clientsCount > recordsCount) {
                clients = clients.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            clients = service.getClients(skippingPagesNumber, recordsCount);
        }

        session.setAttribute("clients", clients);

        return PAGE;
    }
}
