package controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role=request.getParameter("role");
        
        System.out.println("role name "+role);

        
        if (userDAO.usernameExists(username)) {
            request.setAttribute("error", "Username already taken.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(role);

        boolean success = userDAO.registerUser(newUser);
        if (success) {
            request.setAttribute("success", "Registration successful. Please login.");
        } else {
            request.setAttribute("error", "Something went wrong. Try again.");
        }

        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}