package com.brazucadev.userscrud.controllers;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.utils.UserBuilder;
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

    private void writeJsonResponse(HttpServletResponse resp, int status, String json) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
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
        } else if (!users.isEmpty()) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK, new Gson().toJson(users.getFirst()));
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

        if (this.userService.push(user)) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK,
              "{\"type\":\"success\", \"message\":\"User registered successfully!\n\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "{\"type\":\"error\", \"message\":\"Failed to create user!\"}");
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
              "{\"type\":\"success\", \"message\":\"User data updated successfully!\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "{\"type\":\"error\", \"message\":\"Failed to update user data!\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> userId = Optional.ofNullable(req.getParameter("id"));
        if (userId.isEmpty()) {
            writeJsonResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
              "{\"type\":\"error\", \"message\":\"User ID is required!\"}");
            return;
        }

        if (this.userService.remove(userId.get())) {
            writeJsonResponse(resp, HttpServletResponse.SC_OK,
              "{\"type\":\"success\", \"message\":\"User removed successfully!\"}");
        } else {
            writeJsonResponse(resp, HttpServletResponse.SC_NOT_FOUND,
              "{\"type\":\"error\", \"message\":\"Failed to remove user!\"}");
        }
    }
}