package vn.iotstar.dao;

import vn.iotstar.model.User;

public interface UserDao {
	User get(String username);
	boolean existsByUsernameAndEmail(String username, String emailaddress) throws Exception;

    int updatePasswordByUsername(String username, String newPassword) throws Exception;
    
    boolean isIdExists(int id);
    boolean isUserNameExists(String username);
    void registerUser(User user);
}
