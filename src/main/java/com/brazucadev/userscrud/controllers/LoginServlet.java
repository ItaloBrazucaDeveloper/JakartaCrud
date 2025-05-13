package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.services.ILoginService;
import com.brazucadev.userscrud.services.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name="loginServlet", urlPatterns={"/login", "/"})
public class LoginServlet extends HttpServlet {
    ILoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> userAuthenticated = this.loginService.validate(email, password);

        if (userAuthenticated.isEmpty()) {
            req.setAttribute("errorMessage", "Invalid credentials, try again...\n");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            this.loginService.setUserSession(req.getSession(), userAuthenticated.get());
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
}