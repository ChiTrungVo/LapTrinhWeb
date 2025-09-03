package vn.iotstar.service;

import vn.iotstar.model.User;

public interface UserService {
    User login(String username, String password);

    User get(String username);
    boolean isIdExists(int id);
    boolean isUserNameExists(String username);
    void registerUser(User user);
}