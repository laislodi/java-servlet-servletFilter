package com.servlet;

import com.servlet.session.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/private/request")
public class AuthenticatedRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();

        pw.write("{\"request\" : \"Authorized\", \"token\" : \"" + Session.getToken() + "\", \"user\" : \"" +
                Session.getUser() + "\"}");
    }
}
