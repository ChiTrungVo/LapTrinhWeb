package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        try {
            User user = userService.login(username.trim(), password);
            if (user != null) {
                req.getSession(true).setAttribute("account", user);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
                req.setAttribute("username", username);
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("alert", "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
