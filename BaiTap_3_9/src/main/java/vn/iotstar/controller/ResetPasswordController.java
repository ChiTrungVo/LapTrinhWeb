package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserDaoImpl;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDAO = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu không khớp, vui lòng nhập lại.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }

        try {
            int updated = userDAO.updatePasswordByUsername(username, newPassword);
            if (updated > 0) {
                // đổi mật khẩu thành công → quay lại login
                request.setAttribute("alert", "Đổi mật khẩu thành công, mời bạn đăng nhập lại.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Không tìm thấy tài khoản để đổi mật khẩu.");
                request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alert", "Có lỗi xảy ra khi đổi mật khẩu.");
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
        }
    }
}