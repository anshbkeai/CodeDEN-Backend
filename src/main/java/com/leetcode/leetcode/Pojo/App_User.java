package com.leetcode.leetcode.Pojo;

import java.util.Collection;
import java.util.Set;
import  java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.leetcode.leetcode.Pojo.Enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class App_User implements UserDetails{

    @Id
    private  String  User_Id;
    private  String  Name;
    private  String  email;

    @RestResource(exported  =  false)
    private String  password;
    private  Integer  Phone_Number;

    @Enumerated(EnumType.STRING)
    @RestResource(exported = false)
    private  Role role ;

    @RestResource(exported = false)
    private  String  jwt_id;
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Set<Role>  roles =  new  HashSet<>(List.of(role));
        return  roles.stream() 
                .map(role ->  new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return  password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return  email;
    }
}
