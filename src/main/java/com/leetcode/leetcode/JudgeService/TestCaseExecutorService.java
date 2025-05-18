package com.leetcode.leetcode.JudgeService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leetcode.leetcode.Core.Dto.SubmissionDto;
import com.leetcode.leetcode.Core.Pojo.Coder;
import com.leetcode.leetcode.Core.Pojo.Problem;
import com.leetcode.leetcode.Core.Pojo.Submission;
import com.leetcode.leetcode.Core.Pojo.TestCases;
import com.leetcode.leetcode.Core.Repositry.CoderRepositry;
import com.leetcode.leetcode.Core.Repositry.ProblemRepositry;
import com.leetcode.leetcode.Core.Repositry.SubmissionRepositry;
import com.leetcode.leetcode.Core.Repositry.SubmissionRepositry;
import com.leetcode.leetcode.Core.Repositry.TestCasesRepositry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestCaseExecutorService {
  //  private final Repositry.SubmissionRepositry submissionRepositry;


    private  final  CoderRepositry  coderRepositry;
    private  final  TestCasesRepositry testCasesRepositry;
    private  final  ProblemRepositry  problemRepositry;
    private  final CoderExecutorService  coderExecutorService;
    private final SubmissionRepositry submissionRepositry;


    public  TestCaseExecutorService(CoderRepositry  coderRepositry,
                        TestCasesRepositry testCasesRepositry,
                        CoderExecutorService coderExecutorService,
                        ProblemRepositry  problemRepositry,
                        SubmissionRepositry submissionRepositry) {
        this.coderRepositry =  coderRepositry;
        this.testCasesRepositry  =  testCasesRepositry;
        this.submissionRepositry = submissionRepositry;
        this.problemRepositry   = problemRepositry;
        this.coderExecutorService  = coderExecutorService;
        //this.submissionRepositry =  submissionRepositry;
    }

    private  void maptoSubmission(Submission  submission, SubmissionDto submissionDto,String  email ){
        submission.setCode(submissionDto.getCode());
        submission.setLanguage(submissionDto.getLanguage());
        submission.setLocalDate(LocalDate.now());
        submission.setProblem_id(submissionDto.getProblem_id());
        submission.setSubmission_id(UUID.randomUUID().toString());
        submission.setCoder(coderRepositry.findByEmail(email).get());
        //RESULT  TO  be  set  after  th e  excuition 
    }
    public  Map<String,String>  run_code(SubmissionDto  submissionDto, String  email) {
        Map<String,String>  map  =  new  HashMap<>();
        Submission submission   =  new Submission();
        maptoSubmission(submission,submissionDto,email);

       log.info("The cast  submission object "+submission.getProblem_id());

        Problem  problem =  problemRepositry.findById(submissionDto.getProblem_id()).orElseThrow();
        //log.info(problem.toString());
        List<TestCases>  testCases_list  =  testCasesRepositry.findByProblem(problem);

       // log.info(testCases_list.stream().toString());

        int  count  = 0;
        for(TestCases  testCases :  testCases_list) {
            String inuput  =  testCases.getTestcase_input();
            String expected_output = testCases.getTestcase_output();

            log.info("Input "+inuput+" Output "+expected_output);
           // String  output =  coderex.exutue()
           String output  =  coderExecutorService.runCode(submission.getLanguage(), submission.getCode(), inuput);
           log.info("Ouput  Recived  "+output+ " "+output.trim().equals(expected_output.trim()));
            if(output.trim().equals(expected_output.trim())) count++;
            map.put(testCases.getTestcase_id(),output);
        }
        
        if(count  ==  testCases_list.size()) {
            submission.setResults("Passed");
            map.put("Results:","Passed");
        }
        else {
            submission.setResults("Failed");
            map.put("Results:","Failed");
        }
        //Save  Submission 
        submissionRepositry.save(submission);
        Coder  coder =  coderRepositry.findByEmail(email).get();
        coder.getSubmission_list().add(submission);
        coderRepositry.save(coder);
        return map;

         
    }
}
