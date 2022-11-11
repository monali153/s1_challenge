package com.example.challenge.s1_challenge.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authheader = request.getHeader("authorization");
        if(authheader == null || !authheader.startsWith("Bearer")){
           throw new ServletException("Missing or Invalid token");
        }
        else {
            String jwtToken = authheader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("mykey").parseClaimsJws(jwtToken).getBody();
            System.out.println("claims = " + claims);
            request.setAttribute("claims",claims);
            filterChain.doFilter(request,response);
        }
    }
}
