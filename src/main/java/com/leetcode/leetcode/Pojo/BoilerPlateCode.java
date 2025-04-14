package com.leetcode.leetcode.Pojo;

import com.leetcode.leetcode.Pojo.Enums.Language;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BoilerPlateCode {

    @Id
    private  String code_id;
    private  String code_comments;
    @Enumerated
    private  Language  language;
    
    private  String code; //  this  will  contaisn  the   MAin  Code  that  we will  be  Seeing  
}
