package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String idStr = req.getParameter("id"); // Get ID as a String
        String userName = req.getParameter("username"); // Changed to userName
        String pass = req.getParameter("password"); // Changed to pass
        String fullName = req.getParameter("fullname"); // Changed to fullName
        String emailaddress = req.getParameter("email"); // Changed to emailaddress

        // Validate ID as a number
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID phải là một số.");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        try {
            // Check for duplicate ID and username
            if (userService.isIdExists(id)) { // Pass int ID
                req.setAttribute("error", "ID đã tồn tại.");
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
                return;
            }
            if (userService.isUserNameExists(userName)) { // Pass userName
                req.setAttribute("error", "Username đã tồn tại.");
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
                return;
            }

            // Create a new User object with corrected field names
            User newUser = new User();
            newUser.setId(id);
            newUser.setUserName(userName);
            newUser.setPass(pass);
            newUser.setFullName(fullName);
            newUser.setEmail(emailaddress); // Note: Your User model has setEmail, not setEmailaddress

            // Register the new user
            userService.registerUser(newUser);

            // Redirect to a success page or the login page
            req.setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            req.setAttribute("error", "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}