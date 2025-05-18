package com.leetcode.leetcode.JudgeService;

import lombok.Data;

@Data
public class JudgeDto {

    private  String  code;
    private  String  coder_id;
    private  String  Submission_id;
    private  String  problem_id;

    // this  will  Ack  for  the  Submisson  Service  inthe   Core to  Sedn  about  tje   Details  
}
