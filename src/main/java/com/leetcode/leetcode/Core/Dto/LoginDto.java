package com.leetcode.leetcode.Core.Dto;

import com.leetcode.leetcode.Core.Pojo.Enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    private  String email;
    @NotBlank
    private String password;
   
}
