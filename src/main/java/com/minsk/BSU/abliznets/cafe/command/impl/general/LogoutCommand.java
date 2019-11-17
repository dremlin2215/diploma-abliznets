package com.minsk.BSU.abliznets.cafe.command.impl.general;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;

import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/general/authorization.jsp";

    private HttpSession session;

    public LogoutCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() {
        session.invalidate();
        return PAGE;
    }
}
