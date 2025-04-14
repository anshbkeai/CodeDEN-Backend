package com.leetcode.leetcode.Pojo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Problem {

    @Id
    private  String  problem_id;
    private String problem_title;
    @Size(max = 1000000)
    private  String  problem_desc;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL,orphanRemoval =true)
    private  List<TestCases>  testcases_list;

    @OneToMany(mappedBy="problem",cascade =  CascadeType.ALL)
    private  List<Tag>  tags_list;

}
