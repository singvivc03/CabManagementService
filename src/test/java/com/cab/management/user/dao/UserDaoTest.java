package com.cab.management.user.dao;

import com.cab.management.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class UserDaoTest {

    UserDao userDao;

    @BeforeEach
    void beforeEach() {
        userDao = new UserDao();
    }

    @Test
    void createUser_success() {
        User user = User.builder().firstName("Test1").lastName("Test2").build();
        User createdUser = userDao.createUser(user);
        assertThat(createdUser.getId(), notNullValue());
    }

    @Test
    void findUserById_success() {
        User user = User.builder().firstName("Test1").lastName("Test2").build();
        User createdUser = userDao.createUser(user);

        User foundUser = userDao.findUserById(createdUser.getId());
        assertThat(foundUser.getFirstName(), is(createdUser.getFirstName()));
        assertThat(foundUser.getLastName(), is(createdUser.getLastName()));
    }
}