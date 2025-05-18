package com.leetcode.leetcode.Core.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leetcode.leetcode.Core.Pojo.Coder;
import com.leetcode.leetcode.Core.Repositry.CoderRepositry;

@Service
public class UserServiceImplementaton implements  UserDetailsService{

    @Autowired
    private  CoderRepositry coderRepositry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<Coder> coder  =  coderRepositry.findByEmail(username);
        if(!coder.isPresent()) {
            throw  new UsernameNotFoundException("Hey  User  Not  found   404");
        }
        return coder.get();
     }

}
