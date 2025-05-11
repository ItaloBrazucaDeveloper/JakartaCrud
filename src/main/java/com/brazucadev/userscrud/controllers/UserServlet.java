package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.entities.UserBuilder;
import com.brazucadev.userscrud.services.IUserService;
import com.brazucadev.userscrud.services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="userServlet", value="/users")
public class UserServlet extends HttpServlet {
    private IUserService userService;
    private UserBuilder userBuilder;

    public UserServlet() {
        this.userService = new UserService();
        this.userBuilder = new UserBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<User> users = userService.list();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.userBuilder
          .withName(req.getParameter("create-user-name"))
          .withEmail(req.getParameter("create-user-email"))
          .withPassword(req.getParameter("create-user-password"))
          .withRole(Integer.parseInt(req.getParameter("create-user-role")))
          .build();

        boolean wasCreated = this.userService.push(user);

        if (wasCreated) {
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("errorMessage", "Não foi possível criar o usuário!");
            req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
        }
    }
}
