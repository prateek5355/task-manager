//package com.infosys.taskmanager.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private  TokenManager tokenManager;
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public JwtTokenFilter(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        String username = null;
//        String token = null;
//
//        if (authHeader != null && authHeader.contains("Bearer")) {
//            token = authHeader.substring(7);
//            try {
//                username = tokenManager.extractUsername(token);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        if (username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
//            if (tokenManager.isTokenValid(token,userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
////    @Override
////    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
////
////    }
//}
