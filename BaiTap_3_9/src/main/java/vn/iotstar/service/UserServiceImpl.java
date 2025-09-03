package vn.iotstar.service;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserDaoImpl;
import vn.iotstar.model.User;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return null;
        }

        User user = userDao.get(username);
        if (user == null) return null;

        if (password.equals(user.getPass())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
    @Override
    public boolean isIdExists(int id) {
        return userDao.isIdExists(id);
    }

    @Override
    public boolean isUserNameExists(String userName) {
        return userDao.isUserNameExists(userName);
    }

    @Override
    public void registerUser(User user) {
        userDao.registerUser(user);
    }
}