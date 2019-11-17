package com.minsk.BSU.abliznets.cafe.command.impl.specify;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

public class SpecifyNewBonusCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonus_adding_form.jsp";

    @Override
    public String execute() throws ServiceException {
        return PAGE;
    }
}
