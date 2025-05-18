package com.leetcode.leetcode.Core.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leetcode.leetcode.Core.Pojo.Submission;

@RepositoryRestResource
@RepositoryRestController

public interface SubmissionRepositry extends  JpaRepository<Submission,String>{

}
