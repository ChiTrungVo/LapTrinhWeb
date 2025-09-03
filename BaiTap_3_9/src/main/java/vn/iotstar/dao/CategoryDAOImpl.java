package vn.iotstar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.config.DBconnect;
import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.model.Category;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() throws Exception {
        String sql = "SELECT * FROM categories";
        List<Category> list = new ArrayList<>();
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                );
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public Category findById(int id) throws Exception {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public int insert(Category category) throws Exception {
        String sql = "INSERT INTO categories(name, description) VALUES(?, ?)";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(Category category) throws Exception {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws Exception {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (Connection conn = new DBconnect().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}