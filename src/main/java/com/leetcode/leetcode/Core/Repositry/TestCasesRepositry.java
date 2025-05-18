package com.leetcode.leetcode.Core.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.leetcode.leetcode.Core.Pojo.Problem;
import com.leetcode.leetcode.Core.Pojo.TestCases;
import java.util.List;


@Repository
@RestResource
public interface TestCasesRepositry extends  JpaRepository<TestCases,String>{

    List<TestCases> findByProblem(Problem problem);
}
