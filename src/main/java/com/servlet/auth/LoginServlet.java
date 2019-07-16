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
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        * Set the Content Type of the request. In this case, json format
        */
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();

        /*
         * Get the user and password from the request.
         */
        String user = req.getParameter("user");
        String password = req.getParameter("password");

        String dbPassword = TokenDao.findUserPassword(user);

        if (Objects.nonNull(dbPassword) && Objects.equals(password, dbPassword)) {
            String token = TokenDao.generateToken();
            TokenDao.saveUserToken(token, user);
            pw.write("{ \"token\" : \"" + token + "\", \"user\" : \"" + user + "\"}");
            resp.setHeader("token", token);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            pw.write("{\n\"message\": \"Invalid User or Password.\"\n}");
        }
    }
}
