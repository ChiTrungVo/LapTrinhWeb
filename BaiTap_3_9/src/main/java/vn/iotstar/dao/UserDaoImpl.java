package vn.iotstar.dao;

import vn.iotstar.config.DBconnect;
import vn.iotstar.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
	@Override
    public boolean existsByUsernameAndEmail(String username, String email) throws Exception {
        String sql = "SELECT 1 FROM accounts WHERE username = ? AND emailaddress = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public int updatePasswordByUsername(String username, String newPassword) throws Exception {
        String sql = "UPDATE accounts SET pass = ? WHERE username = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            return ps.executeUpdate();
        }
    }

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
    @Override
    public boolean isIdExists(int id) {
        String sql = "SELECT COUNT(*) FROM accounts WHERE id = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        // Thay đổi kiểu ngoại lệ ở đây
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return false;
    }

    // Sửa đổi phương thức isUserNameExists
    @Override
    public boolean isUserNameExists(String username) {
        String sql = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        // Thay đổi kiểu ngoại lệ ở đây
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return false;
    }

    // Sửa đổi phương thức registerUser
    @Override
    public void registerUser(User user) {
        String sql = "INSERT INTO accounts (id, username, pass, fullname, emailaddress) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        // Thay đổi kiểu ngoại lệ ở đây
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}