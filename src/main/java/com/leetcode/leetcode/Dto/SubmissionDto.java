package com.leetcode.leetcode.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leetcode.leetcode.Pojo.Enums.Language;

import lombok.Data;

@Data
public class SubmissionDto {

    @JsonProperty("Code")
    private  String Code;
    private  Language language;
    @JsonProperty("problem_id")
    private  String  Problem_id;
}
