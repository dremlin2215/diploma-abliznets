package com.minsk.BSU.abliznets.cafe.command.impl.specify;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.impl.AbstractCommand;

public class SpecifyNewDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/dish_adding_form.jsp";

    @Override
    public String execute() {
        return PAGE;
    }
}
