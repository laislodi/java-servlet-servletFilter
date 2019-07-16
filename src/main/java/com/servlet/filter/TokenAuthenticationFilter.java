package com.servlet.filter;

import com.servlet.dao.TokenDao;
import com.servlet.session.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/TokenAuthenticationFilter")
public class TokenAuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        context.log("LOG: Iniciando Filtro de token");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();
        /*
         * Clear the Session to make sure one thread is not going to user the authentication from another
         */
        Session.setUser(null);
        Session.setToken(null);
        String token = ((HttpServletRequest) request).getHeader("token");
        String user = TokenDao.findUser(token);

        Session.setToken(token);
        Session.setUser(user);

        if (path.contains("/private/")) {
            if (Objects.nonNull(user)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"message\" : \"Should be authenticated to access this resource\"}");
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        context.log("Finalizando filtro de token");

    }
}
