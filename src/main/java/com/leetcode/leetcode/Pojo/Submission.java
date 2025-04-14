package com.leetcode.leetcode.Pojo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.leetcode.leetcode.Pojo.Enums.Language;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Submission {

    @Id
    private  String  submission_id;

    private  LocalDate  localDate;

    @Enumerated
    private  Language  language;

    private  String  Results; // passed  or  failed   

    private  String Problem_id;

    @ManyToOne
    private  Coder  coder;

    @Length(max = 1000000)
    private  String  code;

    //  this  we  willl  have  the  Dto  of  the   Submission  and  adding  up  the   Transction Management  to  do  so  to run  teh  Code 

}