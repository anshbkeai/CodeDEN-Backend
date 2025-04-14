package com.leetcode.leetcode.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class TestCases {

    @Id
    private  String  testcase_id;
    private  String  testcase_descrpiton;
    private  String testcase_input;
    private  String  testcase_output;

    @ManyToOne
     @JoinColumn(name = "problem_id", nullable = false) 
    private  Problem problem;
}
