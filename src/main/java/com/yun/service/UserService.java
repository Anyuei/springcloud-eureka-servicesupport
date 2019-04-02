package com.yun.service;

import com.yun.entity.User;

import java.util.List;

public interface UserService {
    User insertUser(User user);
    void deleteUserByID(Integer id);
    void updateUserByID(Integer id);
    User retrieveUserByID(Integer id);
    User retrieveUserByName(String name);
    List<User> retrieveUsersByName(String name);
    User retrieveUserByNickname(String nickname);
    List<User> retrieveUsersByNickname(String nickname);
}
