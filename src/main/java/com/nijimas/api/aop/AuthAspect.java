package com.nijimas.api.aop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.nijimas.api.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {

    @Before("execution(* com.nijimas.api.controller.*.*(..))")
    public void authenticate(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new UnauthorizedException("No current HTTP request found, unable to authenticate");
        }
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                FirebaseToken decodeToken = FirebaseAuth.getInstance().verifyIdToken(token);
                request.setAttribute("ownUid", decodeToken.getUid());
            } catch (FirebaseAuthException e) {
                throw new UnauthorizedException("Invalid or expired token");
            }
        } else {
            throw new UnauthorizedException("Missing or invalid Authorization header");
        }
    }
}
