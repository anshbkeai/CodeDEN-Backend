package com.leetcode.leetcode.Core.Rest_Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetcode.leetcode.Core.Dto.LoginDto;
import com.leetcode.leetcode.Core.Dto.SignupDto;
import com.leetcode.leetcode.Core.Service.AuthService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthRoute {

    private  final   AuthService  authService;

    public  AuthRoute(AuthService authService  ) {
        this.authService  =  authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto) {
        //TODO: process POST request
        Map<String,String>  map  = authService.customAuth(loginDto);
        
        return new  ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody SignupDto loginDto) {
        //TODO: process POST request
        Boolean  B  =  authService.Signup(loginDto);
        if(!B) return  new  ResponseEntity<>("Hey  User  Already  Exist", HttpStatus.CONFLICT);
        LoginDto  dto   =  new LoginDto();
        dto.setEmail(loginDto.getEmail());
        dto.setPassword(loginDto.getPassword());
        return  new ResponseEntity<>(authService.customAuth(dto).get("Token"), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public  ResponseEntity<String> test() {
        return  new  ResponseEntity<String>("Hey  this  is   the   Test ROUTE "+HttpStatus.OK, HttpStatus.OK);
    }

    
}
