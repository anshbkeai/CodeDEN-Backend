package com.leetcode.leetcode.Core.Pojo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import  jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Coder extends  App_User{


    
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
        name = "User_Badge", // Name of the intermediate table
        joinColumns = @JoinColumn(name = "user_id"), // FK for User
        inverseJoinColumns = @JoinColumn(name = "badge_id") // FK for Role
    )
    List<Badge>  badges_earned;

    @OneToMany(mappedBy = "coder", cascade = CascadeType.ALL,orphanRemoval =true)
    private  List<Submission>  submission_list;

}
