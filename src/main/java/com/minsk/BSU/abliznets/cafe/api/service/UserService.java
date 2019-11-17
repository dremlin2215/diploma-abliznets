package com.minsk.BSU.abliznets.cafe.api.service;

import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> authorize(String login, String password) throws ServiceException;

    List<User> getClients() throws ServiceException;

    void updateUser(User user) throws ServiceException;

    List<User> getUsersByIds(List<Integer> userIDs) throws ServiceException;

    List<User> getClients(int skippingPagesNumber, int recordsCount) throws ServiceException;
}
