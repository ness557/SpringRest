package ness.service;

import ness.model.User;

import java.util.List;

public interface UserService {

    int addUser(User user);
    int updateUser(User user);
    int removeUser(User user);
    int saveOrUpdate(User user);
    int removeUser(int id);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getUserList();
}
