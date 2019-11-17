package com.minsk.BSU.abliznets.cafe.command.factory;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.CommandFactoryException;
import com.minsk.BSU.abliznets.cafe.command.impl.add.AddBonusCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.add.AddDishCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.add.OrderCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.delete.*;
import com.minsk.BSU.abliznets.cafe.command.impl.get.*;
import com.minsk.BSU.abliznets.cafe.command.impl.update.LeaveCommentCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.add.SaveDishOrderCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.delete.*;
import com.minsk.BSU.abliznets.cafe.command.impl.general.AuthorizeCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.general.ChooseLanguageCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.general.LogoutCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.get.*;
import com.minsk.BSU.abliznets.cafe.command.impl.manage.ManageClientsInformationCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.manage.ManageDishInformationCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.specify.SpecifyNewBonusCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.specify.SpecifyNewDishCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.update.RateOrderCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.update.SaveClientChangesCommand;
import com.minsk.BSU.abliznets.cafe.command.impl.update.SaveDishChangesCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Command create(String commandName) throws CommandFactoryException {
        if (commandName == null) {
            throw new CommandFactoryException("Miss commands name.");
        }

        Command command;
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case AUTHORIZE:
                command = new AuthorizeCommand(request);
                break;
            case LOGOUT:
                command = new LogoutCommand(request.getSession());
                break;
            case GET_CLIENTS:
                command = new GetClientsCommand(request);
                break;
            case GET_BONUSES_ADMIN:
                command = new GetBonusesAdminCommand(request);
                break;
            case MANAGE_CLIENT_INFORMATION:
                command = new ManageClientsInformationCommand(request);
                break;
            case SAVE_CLIENT_CHANGES:
                command = new SaveClientChangesCommand(request);
                break;
            case GET_CLIENT_MENU:
                command = new GetClientMenuCommand(request);
                break;
            case SAVE_DISH_ORDER:
                command = new SaveDishOrderCommand(request);
                break;
            case SHOW_BASKET:
                command = new ShowBasketCommand(request);
                break;
            case REMOVE_DISH_ORDER:
                command = new RemoveDishOrderCommand(request);
                break;
            case ORDER:
                command = new OrderCommand(request);
                break;
            case REMOVE_ALL_DISH_ORDER:
                command = new RemoveAllDishOrderCommand(request);
                break;
            case GET_BONUSES_CLIENT:
                command = new GetBonusesClientCommand(request);
                break;
            case DELETE_BONUS:
                command = new DeleteBonusCommand(request);
                break;
            case SPECIFY_NEW_BONUS:
                command = new SpecifyNewBonusCommand();
                break;
            case ADD_BONUS:
                command = new AddBonusCommand(request);
                break;
            case GET_ALL_DISHES:
                command = new GetAllDishesCommand(request);
                break;
            case MANAGE_DISH_INFORMATION:
                command = new ManageDishInformationCommand(request);
                break;
            case SAVE_DISH_CHANGES:
                command = new SaveDishChangesCommand(request);
                break;
            case DELETE_DISH:
                command = new DeleteDishCommand(request);
                break;
            case SPECIFY_NEW_DISH:
                command = new SpecifyNewDishCommand();
                break;
            case ADD_DISH:
                command = new AddDishCommand(request);
                break;
            case GET_PREVIOUS_ORDERS:
                command = new GetPreviousOrdersCommand(request);
                break;
            case RATE_ORDER:
                command = new RateOrderCommand(request);
                break;
            case LEAVE_COMMENT:
                command = new LeaveCommentCommand(request);
                break;
            case GET_GLOBAL_ORDERS:
                command = new GetGlobalOrdersCommand(request);
                break;
            case GET_CURRENT_ORDERS:
                command = new GetCurrentOrdersCommand(request);
                break;
            case DELETE_ORDER:
                command = new DeleteOrderCommand(request);
                break;
            case CHOOSE_LANGUAGE:
                command = new ChooseLanguageCommand(request);
                break;
            default:
                throw new CommandFactoryException("Invalid commands name: " + commandType.name());
        }

        return command;
    }
}
