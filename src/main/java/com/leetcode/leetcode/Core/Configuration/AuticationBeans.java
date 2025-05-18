package com.leetcode.leetcode.Core.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leetcode.leetcode.Core.Service.UserServiceImplementaton;

@Configuration
public class AuticationBeans {

    private  final  UserServiceImplementaton userServiceImplementaton;
    public  AuticationBeans(UserServiceImplementaton  userServiceImplementaton) {
        this.userServiceImplementaton   =  userServiceImplementaton;
    }
    @Bean
    public  AuthenticationManager  authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public  AuthenticationProvider  daoAuthenticationProvider() {
        DaoAuthenticationProvider  daoAuthenticationProvider =  new   DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userServiceImplementaton);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return  daoAuthenticationProvider;
    }

    @Bean
    public  PasswordEncoder  passwordEncoder() {
        return  new  BCryptPasswordEncoder(11);
    }
}
