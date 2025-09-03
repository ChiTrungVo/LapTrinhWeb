package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.dao.CategoryDAOImpl;
import vn.iotstar.model.Category;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/views/category-form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Category cat = categoryDAO.findById(id);
                    request.setAttribute("category", cat);
                    request.getRequestDispatcher("/views/category-form.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    categoryDAO.delete(id);
                    response.sendRedirect("categories");
                    break;
                default: // list
                    List<Category> list = categoryDAO.findAll();
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/views/category-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Category cat = new Category();
        cat.setName(name);
        cat.setDescription(description);

        try {
            if (id == null || id.isEmpty()) {
                categoryDAO.insert(cat);
            } else {
                cat.setId(Integer.parseInt(id));
                categoryDAO.update(cat);
            }
            response.sendRedirect("categories");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}