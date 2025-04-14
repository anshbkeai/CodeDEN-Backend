package com.leetcode.leetcode.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leetcode.leetcode.Dto.LoginDto;
import com.leetcode.leetcode.Dto.SignupDto;
import com.leetcode.leetcode.Pojo.Coder;
import com.leetcode.leetcode.Pojo.Enums.Role;
import com.leetcode.leetcode.Repositry.CoderRepositry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    private  final  CoderRepositry  coderRepositry;
    private  final  AuthenticationManager   authenticationManager;
    private  final  JwtService jwtService;
    private  final  PasswordEncoder passwordEncoder;
    
    public AuthService(CoderRepositry  coderRepositry,
                        AuthenticationManager  authenticationManager,
                        JwtService jwtService,
                        PasswordEncoder  passwordEncoder) {
        this.coderRepositry   =  coderRepositry;
        this.authenticationManager  = authenticationManager;
        this.jwtService   =  jwtService;
        this.passwordEncoder  =  passwordEncoder;
    }

    public Map<String,String>  customAuth(LoginDto loginDto) {
        Map<String,String>  map  =  new  HashMap<>();
        log.info("User  Login  Attempt  "+loginDto.toString());
        Authentication  authentication  = authenticationManager.authenticate( new  UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(!authentication.isAuthenticated()) {
            //  return  me  the   Fialded  ma
            throw  new  UsernameNotFoundException("Wrong  ");
        }
        //  th e  Jwt  Service  and   then  
        //  we  need to  return  th e  TOken  as   we   get or  Onboeard   
        Coder  coder =  coderRepositry.findByEmail(loginDto.getEmail()).get();
        String  token  =  jwtService.Genrate_JWT(coder.getEmail(), coder.getUser_Id());
        log.warn("TOken "+token);
        map.put("Token",token);
        map.put("Login_Status",  "OK");
        return  map;


    }

    public boolean  Signup(SignupDto  loginDto) {
         if(coderRepositry.findByEmail(loginDto.getEmail()).isPresent())  return  false;  
      
       Coder  coder  =  new Coder();
       coder.setEmail(loginDto.getEmail());
       coder.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        coder.setRole(Role.Coder);
       coder.setUser_Id(UUID.randomUUID().toString());
       coder.setName(loginDto.getUsername());

        coderRepositry.save(coder);
      return  true;
        
    }

    
    //private  final JwtService  
    
}
