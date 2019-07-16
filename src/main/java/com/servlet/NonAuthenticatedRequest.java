package com.servlet;

import com.servlet.session.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/request")
public class NonAuthenticatedRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();

        String user = Session.getUser();
        if (Objects.isNull(user)) {
            user = "user";
        }
        pw.write("{\"message\" : \"Non Authenticated Request\", \"token\" : \"" + Session.getToken() +
                "\", \"user\" : \"" + Session.getUser() + "\"}");
    }
}
