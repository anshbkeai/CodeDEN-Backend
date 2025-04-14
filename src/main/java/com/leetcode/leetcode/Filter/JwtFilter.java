package com.leetcode.leetcode.Filter;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.leetcode.leetcode.Service.JwtService;
import com.leetcode.leetcode.Service.UserServiceImplementaton;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtFilter extends  OncePerRequestFilter{

    private  final  JwtService  jwtService;
    private  final  UserServiceImplementaton  userServiceImplementaton;

    public  JwtFilter(JwtService  jwtService , 
                        UserServiceImplementaton  userServiceImplementaton) {
            this.jwtService   =  jwtService;
            this.userServiceImplementaton  =  userServiceImplementaton;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
         String  request_uri  =  request.getRequestURI();
            if(request_uri.startsWith("/app/")  ||  request_uri.startsWith("/api/")  ||  request_uri.startsWith("/login") ||  request_uri.startsWith("/oauth") ||
                        request_uri.startsWith("/v3/") ||  request_uri.startsWith( "/swagger-ui/") 
            ){
                filterChain.doFilter(request, response);
                return;
            }
             
            //okay  so  movfing  thos Apporach  about  rather  than  
            String  token  = "";
            String  user_email = "";
            String  authheader =  request.getHeader("Authorization");
            if(!authheader.isEmpty() &&  authheader.startsWith("Bearer")) {
                logger.info(authheader);
    
                token  =  authheader.substring(7);
                logger.info("The  Token   is  "+token);
                user_email =  jwtService.getUserId(token);
                logger.info(user_email);
            }
            // if(token  != null) {
            //     logger.info(token);
            //     user_email =  jwtServiceImplementaiton.getUserId(token);
            //  logger.info(user_email);
            // }
            
            if(!user_email.isEmpty() &&  SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails app_User   =  userServiceImplementaton.loadUserByUsername(user_email);
    
                
    
                if (jwtService.validate(app_User, token)) {
                    
                    UsernamePasswordAuthenticationToken  token2 =  new UsernamePasswordAuthenticationToken(app_User,null,app_User.getAuthorities());
                     token2.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(token2);
        
                        logger.info("Authentication successful for user: " + user_email);
                }
            }
    
            filterChain.doFilter(request, response);
    }

}
