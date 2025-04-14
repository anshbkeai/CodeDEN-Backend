package com.leetcode.leetcode.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.leetcode.leetcode.Pojo.BoilerPlateCode;

@Repository
@RepositoryRestResource
public interface BoilerPlateRepositry extends  JpaRepository<BoilerPlateCode,String>{

}
