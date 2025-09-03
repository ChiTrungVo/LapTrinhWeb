package vn.iotstar.controller;

import java.io.IOException;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/auth/forgot")
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiện form nhập username + email
        request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String emailaddress = request.getParameter("emailaddress");

        try {
        	UserDao userDAO = new UserDaoImpl();
        	boolean valid = userDAO.existsByUsernameAndEmail(username, emailaddress);

        	if (valid) {
        	    request.setAttribute("username", username);
        	    request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
        	} else {
        	    request.setAttribute("alert", "Tên đăng nhập hoặc email không đúng.");
        	    request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
            request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
        }
    }
}