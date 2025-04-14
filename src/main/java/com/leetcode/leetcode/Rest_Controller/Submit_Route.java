package com.leetcode.leetcode.Rest_Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetcode.leetcode.Dto.SubmissionDto;
import com.leetcode.leetcode.Service.TestCaseExecutorService;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/submit")
@Slf4j
public class Submit_Route {

    private  final  TestCaseExecutorService testCaseExecutorService;
    public Submit_Route(TestCaseExecutorService testCaseExecutorService) {
        this.testCaseExecutorService   =  testCaseExecutorService;
    }

    private  String  getAuthCoder() {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authcation  Object  "+authentication.getName());
        return  authentication.getName();
    }

    @PostMapping("/v1")
    public Map<String,String> submit(@RequestBody SubmissionDto submissionDto) {
        //TODO: process POST request
        //  we  need  to  go  ghat  
        
        String coder_mail  =  getAuthCoder();
        log.info(coder_mail);
        log.warn(submissionDto.toString());
        return  testCaseExecutorService.run_code(submissionDto, coder_mail);
    }
    
}
