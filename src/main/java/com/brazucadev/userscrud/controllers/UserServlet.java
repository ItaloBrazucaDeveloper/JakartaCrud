package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.services.IUserService;
import com.brazucadev.userscrud.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="userServlet", value="/users")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<User> users = userService.list();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
    }
}
