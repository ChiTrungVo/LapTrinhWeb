package baitap1.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); // không tạo mới

        if (session != null && session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2>Xin chào, " + name + "</h2>");
                out.println("<a href='logout'>Đăng xuất</a>");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }
}
