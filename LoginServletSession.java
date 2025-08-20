package baitap1.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Demo: hard-code user/pass
        if ("trung".equals(username) && "123".equals(password)) {
            HttpSession session = request.getSession();       // tạo session
            session.setAttribute("name", username);            // lưu username
            response.sendRedirect("profile");                  // sang profile
        } else {
            request.setAttribute("msg", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
