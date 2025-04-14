package com.leetcode.leetcode.Rest_Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetcode.leetcode.Service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
public class LogoutRoute {

    private  final  JwtService  jwtService;

    public  LogoutRoute(JwtService  jwtService){
        this.jwtService   =  jwtService;
    }

    @PostMapping("/api/logout/v1")
    public ResponseEntity<Map<String,String>> Logout(HttpServletRequest  request) {
        //TODO: process POST request
        //  we  need  to  Invalide the   Token  and  for  that  we  need  to 
         //okay  so  movfing  thos Apporach  about  rather  than  
         Map<String,String>  map  =  new  HashMap<>();
         String  token  = "";
         String  user_email = "";
         String  authheader =  request.getHeader("Authorization");
         if(!authheader.isEmpty() &&  authheader.startsWith("Bearer")) {
             log.info(authheader);
 
             token  =  authheader.substring(7);
             log.info("The  Token   is  "+token);
            // user_email =  jwtService.getUserId(token);
             //logger.info(user_email);
             boolean  invalidate_token =  jwtService.Invalidate_token(token);
             map.put("token_status",""+invalidate_token);
             map.put("Token ",  "");
             return new  ResponseEntity<Map<String,String>>(map, HttpStatus.OK);

         }
        return new  ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
