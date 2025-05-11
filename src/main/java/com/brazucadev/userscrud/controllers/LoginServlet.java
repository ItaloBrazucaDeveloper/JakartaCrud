package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.services.ILoginService;
import com.brazucadev.userscrud.services.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="loginServlet", value="/login")
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

        boolean isAuthenticated = loginService.login(email, password, req.getSession());

        if (!isAuthenticated) {
            req.setAttribute("errorMessage", "Email e/ou senha inválidos!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
}