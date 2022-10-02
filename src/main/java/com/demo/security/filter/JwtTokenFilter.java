package com.demo.security.filter;

import com.demo.security.service.UserDetailsServiceImpl;
import com.demo.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public String currentUsername;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer")){

            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }


         String token = authorizationHeader.substring(7);
         String username = jwtUtil.extractUsername(token);

         if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
             System.out.println(userDetails);
             if(jwtUtil.validateToken(token,userDetails)){
                 this.currentUsername = username;
                 UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities() );
                 upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                 SecurityContextHolder.getContext().setAuthentication(upassToken);
             }
         }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}
