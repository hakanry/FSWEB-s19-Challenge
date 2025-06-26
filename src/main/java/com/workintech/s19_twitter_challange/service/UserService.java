package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(long id);
    User save(User user);
    User update(long userId,User user);
    User delete(long id);
}
