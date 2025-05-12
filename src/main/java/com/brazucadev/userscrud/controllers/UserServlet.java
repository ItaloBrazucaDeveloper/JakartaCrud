package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.entities.UserBuilder;
import com.brazucadev.userscrud.services.IUserService;
import com.brazucadev.userscrud.services.UserService;
import com.brazucadev.userscrud.utils.JsonBodyParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    private IUserService userService = new UserService();
    private UserBuilder userBuilder = new UserBuilder();

    private boolean isUserAuthenticated(HttpServletRequest req) {
        return req.getSession().getAttribute("userRole") != null;
    }

    private void writeJsonResponse(HttpServletResponse resp, int status, String json) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (!isUserAuthenticated(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

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
        } else if (!users.isEmpty()) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK, new Gson().toJson(users.getFirst()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isUserAuthenticated(req)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = this.userBuilder
            .withName(req.getParameter("create-user-name"))
            .withEmail(req.getParameter("create-user-email"))
            .withPassword(req.getParameter("create-user-password"))
            .withRole(Integer.parseInt(req.getParameter("create-user-role")))
            .build();

        if (this.userService.push(user)) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK,
              "{\"type\":\"success\", \"message\":\"Usuário registrado com sucesso!\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "{\"type\":\"error\", \"message\":\"Não foi possível criar o usuário!\"}");
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isUserAuthenticated(req)) {
            writeJsonResponse(resp, HttpServletResponse.SC_UNAUTHORIZED, "{\"error\":\"Access denied!\"}");
            return;
        }
        JsonObject jsonBody = JsonBodyParser.parseBodyAsJson(req);

        User user = this.userBuilder
            .withId(Long.parseLong(jsonBody.get("update-user-id").getAsString()))
            .withName(jsonBody.get("update-user-name").getAsString())
            .withEmail(jsonBody.get("update-user-email").getAsString())
            .withPassword(jsonBody.get("update-user-password").getAsString())
            .withRole(Integer.parseInt(jsonBody.get("update-user-role").getAsString()))
            .build();

        if (this.userService.refresh(user)) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK,
              "{\"type\":\"success\", \"message\":\"Usuário atualizado com sucesso!\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "{\"type\":\"error\", \"message\":\"Não foi possível atualizar os dados do usuário!\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isUserAuthenticated(req)) {
            writeJsonResponse(resp, HttpServletResponse.SC_UNAUTHORIZED, "{\"error\":\"Access denied!\"}");
            return;
        }

        String idParam = req.getParameter("id");
        if (idParam == null) {
            writeJsonResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
              "{\"type\":\"error\", \"message\":\"Id de usuário não informado.\"}");
            return;
        }
        long userId = Long.parseLong(req.getParameter("id"));

        if (this.userService.remove(userId)) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK,
              "{\"type\":\"success\", \"message\":\"Usuário removido com sucesso!\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_NOT_FOUND,
              "{\"type\":\"error\", \"message\":\"Não foi possível deletar o usuário!\"}");
        }
    }
}