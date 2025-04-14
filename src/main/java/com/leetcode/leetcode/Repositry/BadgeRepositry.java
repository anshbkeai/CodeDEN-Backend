package com.leetcode.leetcode.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.stereotype.Repository;

import com.leetcode.leetcode.Pojo.Badge;

@Repository
@RepositoryRestResource
public interface BadgeRepositry extends  JpaRepository<Badge,String>{

}
