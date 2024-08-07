package com.nijimas.api.api;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import java.io.IOException;

public class TokenVerifyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // Swagger関連のパスを除外
        if (path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui.html")) {
            chain.doFilter(request, response);
            return;
        }

        String token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                httpRequest.setAttribute("ownUid", decodedToken.getUid());
                chain.doFilter(request, response);
            } catch (FirebaseAuthException e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            }
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
        }
    }
}
