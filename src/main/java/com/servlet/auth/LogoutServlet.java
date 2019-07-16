package com.servlet.auth;

import com.servlet.dao.TokenDao;
import com.servlet.session.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/private/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();

        String token = Session.getToken();
        TokenDao.removeToken(Session.getToken());

        pw.write("{ \"message\" : \"Bye!\", \"token\" : \"" + token + "\"}");
    }
}
