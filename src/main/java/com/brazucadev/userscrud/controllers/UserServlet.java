package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.entities.UserBuilder;
import com.brazucadev.userscrud.services.IUserService;
import com.brazucadev.userscrud.services.UserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        Object flash = req.getSession().getAttribute("flashMessage");
        if (flash != null) {
            req.setAttribute("flashMessage", flash);
            req.getSession().removeAttribute("flashMessage");
        }

        Optional<String> userId = Optional.ofNullable(req.getParameter("id"));
        List<User> users = userService.list(userId);

        if (users.size() > 1) {
            req.setAttribute("users", users);
            req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
            return;
        }

        if (users.size() == 1) {
            User user = users.getFirst();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(new Gson().toJson(user));
        }
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
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"type\":\"success\", \"message\":\"Usuário registrado com sucesso!\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"type\":\"error\", \"message\":\"Não foi possível criar o usuário!\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.userBuilder
            .withId(Long.parseLong(req.getParameter("update-user-id")))
            .withName(req.getParameter("update-user-name"))
            .withEmail(req.getParameter("update-user-email"))
            .withPassword(req.getParameter("update-user-password"))
            .withRole(Integer.parseInt(req.getParameter("update-user-role")))
            .build();

        boolean wasRefreshed = this.userService.refresh(user);

        if (wasRefreshed) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"type\":\"success\", \"message\":\"Usuário atualizado com sucesso!\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"type\":\"error\", \"message\":\"Não foi possível atualizar os dados do usuário!\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Id de usuário não informado.");
            resp.getWriter().write("{\"type\":\"error\", \"message\":\"Id de usuário não informado.\"}");
            return;
        }

        long userId = Long.parseLong(req.getParameter("id"));

        boolean wasRemoved = this.userService.remove(userId);
        if (wasRemoved) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"type\":\"success\", \"message\":\"Usuário removido com sucesso!\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"type\":\"error\", \"message\":\"Não foi possível deletar o usuário!\"}");
        }
    }
}