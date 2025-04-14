package com.leetcode.leetcode.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.leetcode.leetcode.Pojo.Tag;

@Repository
@RestResource
public interface TagRepositry extends  JpaRepository<Tag,String>{

}
