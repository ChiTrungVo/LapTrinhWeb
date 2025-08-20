package baitap1.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

       
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        
        if ("trung".equals(user) && "123".equals(pass)) {
           
            Cookie cookie = new Cookie("username", user);
          
            cookie.setMaxAge(30);
        
            resp.addCookie(cookie);

          
            String cp = req.getContextPath(); 
            resp.sendRedirect(cp + "/hello");
        } else {
           
            String cp = req.getContextPath();
            resp.sendRedirect(cp + "/login.html");
        }
    }
}
