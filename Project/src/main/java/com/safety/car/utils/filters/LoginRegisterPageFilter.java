package com.safety.car.utils.filters;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginRegisterPageFilter extends GenericFilterBean {

    //TODO REMOVE
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && (requestURI.equals("/login") || requestURI.equals("/register"))) {

            System.out.println("user is authenticated, but trying to access login/register page, redirecting to /");
            ((HttpServletResponse) response).sendRedirect("/");
        }

        chain.doFilter(request, response);
    }
}