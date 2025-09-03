package vn.iotstar.dao;
import java.util.List;
import vn.iotstar.model.Category;

public interface CategoryDAO {
    List<Category> findAll() throws Exception;
    Category findById(int id) throws Exception;
    int insert(Category category) throws Exception;
    int update(Category category) throws Exception;
    int delete(int id) throws Exception;
}