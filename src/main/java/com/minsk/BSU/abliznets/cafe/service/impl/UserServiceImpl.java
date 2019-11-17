package com.minsk.BSU.abliznets.cafe.service.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.service.UserService;
import com.minsk.BSU.abliznets.cafe.entitie.user.User;
import com.minsk.BSU.abliznets.cafe.entitie.user.UserRole;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryFactory;
import com.minsk.BSU.abliznets.cafe.repository.specification.user.UserByIDsSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.user.UserByLoginAndPasswordSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.user.UserByRoleSpecification;
import com.minsk.BSU.abliznets.cafe.repository.specification.user.UserByRoleWithLimitSpecification;
import com.minsk.BSU.abliznets.cafe.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final int FIRST = 0;

    @Override
    public Optional<User> authorize(String login, String password) throws ServiceException {
        Optional<User> user;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            List<User> users = userRepository.query(new UserByLoginAndPasswordSpecification(login, password));

            if (!users.isEmpty()) {
                user = Optional.of(users.get(FIRST));
            } else {
                user = Optional.empty();
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Authorization error.", e);
        }

        return user;
    }

    @Override
    public List<User> getClients() throws ServiceException {
        List<User> clients;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            clients = userRepository.query(new UserByRoleSpecification(UserRole.CLIENT));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients error.", e);
        }

        return clients;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Updating user error.", e);
        }
    }

    @Override
    public List<User> getUsersByIds(List<Integer> userIDs) throws ServiceException {
        List<User> users;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            users = userRepository.query(new UserByIDsSpecification(userIDs));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting users by ids error.", e);
        }

        return users;
    }

    @Override
    public List<User> getClients(int skippingPagesNumber, int recordsCount) throws ServiceException {
        List<User> clients;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            clients = userRepository.query(new UserByRoleWithLimitSpecification(
                    UserRole.CLIENT,
                    skippingPagesNumber,
                    recordsCount
            ));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients error.", e);
        }

        return clients;
    }
}
