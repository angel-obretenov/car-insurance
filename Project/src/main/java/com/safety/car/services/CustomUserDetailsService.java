package com.safety.car.services;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

public class CustomUserDetailsService extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserService userService;

    public CustomUserDetailsService() {
        super.setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        Principal principal = request.getUserPrincipal();
//        UserDetails user = userService.getByEmail(principal.getName());
        UserDetails user = userService.getByEmail(request.getParameter("username"));
        System.out.println(request.getParameter("username"));

        if (!user.getEnabled()){
            throw new NotFoundException(format("User with email %s not found!", request.getParameter("username")));
        }

        return super.attemptAuthentication(request, response);
    }
}












