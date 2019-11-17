package com.minsk.BSU.abliznets.cafe.command.impl.general;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.page.Language;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChooseLanguageCommand extends AbstractCommand implements Command {
    private HttpServletRequest request;

    public ChooseLanguageCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String langStr = request.getParameter("language");
        Language language = Language.valueOf(langStr.toUpperCase());

        HttpSession session = request.getSession();
        session.setAttribute("locale", language.getLocale());

        User user = (User) session.getAttribute("user");
        UserRole role = user.getRole();

        return findPageByRole(role);
    }
}
