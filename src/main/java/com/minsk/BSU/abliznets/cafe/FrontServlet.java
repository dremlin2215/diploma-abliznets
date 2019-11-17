package com.minsk.BSU.abliznets.cafe;

import com.minsk.BSU.abliznets.cafe.api.Command;
import com.minsk.BSU.abliznets.cafe.command.factory.CommandFactory;
import com.minsk.BSU.abliznets.cafe.connection.pool.ConnectionPool;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.page.NavigationWay;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class FrontServlet extends HttpServlet {
    private static final String CLIENT_ERROR_PAGE = "/view/page/client/client_error.jsp";
    private static final String ADMIN_ERROR_PAGE = "/view/page/administrator/admin_error.jsp";
    private static final String DEFAULT_PAGE = "/view/page/general/authorization.jsp";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public void destroy() {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            pool.closeAll();
        } catch (SQLException e) {
            LOGGER.error("Closing database connections exception", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page;
        String navigationWayParam = req.getParameter("navigationWay");
        NavigationWay navigationWay = NavigationWay.valueOf(navigationWayParam.toUpperCase());
        try {
            String commandName = req.getParameter("command");
            CommandFactory commandFactory = new CommandFactory(req);
            Command command = commandFactory.create(commandName);

            page = command.execute();
        } catch (ServiceException e) {
            LOGGER.error("", e);

            page = handleErrorMessage(e.getMessage(), req);
            navigationWay = NavigationWay.FORWARD;
        } catch (Exception e) {
            LOGGER.error("", e);

            page = handleErrorMessage("Internal server error.", req);
            navigationWay = NavigationWay.FORWARD;
        }

        dispatch(req, resp, page, navigationWay);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page,
                          NavigationWay navigationWay) throws ServletException, IOException {
        switch (navigationWay) {
            case FORWARD:
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
                requestDispatcher.forward(req, resp);
                break;
            case REDIRECT:
                String url = req.getContextPath() + page;
                resp.sendRedirect(url);
                break;
        }
    }

    private String handleErrorMessage(String errorMessage, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("errorMessage", errorMessage);

        User user = (User) session.getAttribute("user");
        UserRole role = user.getRole();

        String page;
        switch (role) {
            case CLIENT:
                page = CLIENT_ERROR_PAGE;
                break;
            case ADMINISTRATOR:
                page = ADMIN_ERROR_PAGE;
                break;
            default:
                page = DEFAULT_PAGE;
        }

        return page;
    }
}
