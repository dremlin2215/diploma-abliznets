package com.minsk.BSU.abliznets.cafe.command.impl.general;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.api.service.UserService;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.page.Language;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.service.impl.UserServiceImpl;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthorizeCommand extends AbstractCommand implements Command {
    private static final Language LANGUAGE = Language.ENGLISH;

    private HttpServletRequest request;

    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        Optional<User> receivedUser = userService.authorize(login, password);

        HttpSession session = request.getSession();

        UserRole role;
        if (receivedUser.isPresent()) {
            User user = receivedUser.get();
            role = handleUser(user);
        } else {
            session.setAttribute("error", "Wrong login or password!");
            role = UserRole.ANONYMOUS;
        }

        return findPageByRole(role);
    }

    private UserRole handleUser(User user) {
        HttpSession session = request.getSession();

        UserRole currentRole = user.getRole();
        boolean userIsNotBannedClient = currentRole.equals(UserRole.CLIENT) && !user.getBanned();
        boolean userIsAdmin = currentRole.equals(UserRole.ADMINISTRATOR);

        UserRole finalRole;
        if (userIsNotBannedClient || userIsAdmin) {
            session.setAttribute("user", user);
            session.setAttribute("locale", LANGUAGE.getLocale());
            finalRole = currentRole;
        } else {
            session.setAttribute("error", "User is banned!");
            finalRole = UserRole.ANONYMOUS;
        }

        return finalRole;
    }
}
