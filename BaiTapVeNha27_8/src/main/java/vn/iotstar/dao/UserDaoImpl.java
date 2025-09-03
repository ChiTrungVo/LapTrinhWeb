package vn.iotstar.dao;

import vn.iotstar.config.DBconnect;
import vn.iotstar.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT id, username, pass, fullname FROM accounts WHERE username = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("username"));
                    user.setPass(rs.getString("pass"));
                    user.setFullName(rs.getString("fullname"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}