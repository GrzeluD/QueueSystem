package com.queuesystem.security.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Objects;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String redirectUrl = null;
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
            redirectUrl = "/console";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            redirectUrl = "/home";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SERVICEMAN"))) {
            redirectUrl = "/service";
        }

        response.sendRedirect(Objects.requireNonNullElse(redirectUrl, "/error"));
    }
}