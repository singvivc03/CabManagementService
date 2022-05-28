package com.cab.management.user.dao;

import com.cab.management.user.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDao {

    private final Map<String, User> users = new HashMap<>();

    public User createUser(final User user) {
        String id = UUID.randomUUID().toString();
        User createdUser = user.toBuilder().id(id).build();
        users.put(id, createdUser);
        return createdUser;
    }

    public User findUserById(final String userId) {
        return users.get(userId);
    }
}
