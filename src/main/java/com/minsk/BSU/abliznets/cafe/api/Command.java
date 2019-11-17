package com.minsk.BSU.abliznets.cafe.api;

import com.minsk.BSU.abliznets.cafe.service.ServiceException;

public interface Command {
    String execute() throws ServiceException;
}
