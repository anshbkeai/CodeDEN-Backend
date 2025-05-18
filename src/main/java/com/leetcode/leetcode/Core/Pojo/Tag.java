package com.leetcode.leetcode.Core.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Tag {

    @Id
    private  String  tag_id;
    private  String  tag_name;
    private  String  tag_desc; //  tag  description  that  to  be Provided  

    @ManyToOne
     @JoinColumn(name = "problem_id", nullable = false) 
    private  Problem problem;
}
