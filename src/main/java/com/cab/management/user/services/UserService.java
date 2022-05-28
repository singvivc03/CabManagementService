package com.cab.management.user.services;

import com.cab.management.user.dao.UserDao;
import com.cab.management.user.exceptions.UserNotFoundException;
import com.cab.management.user.model.User;

public class UserService {

    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(final User user) {
        return this.userDao.createUser(user);
    }

    public User findUserById(final String userId) {
        User foundUser = this.userDao.findUserById(userId);
        if (foundUser == null)
            throw new UserNotFoundException("user with id " + userId + " does not exist");
        return foundUser;
    }
}
