package com.leetcode.leetcode.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.leetcode.leetcode.Pojo.Coder;
import java.util.List;
import java.util.Optional;


@Repository
@RepositoryRestResource
public interface CoderRepositry extends  JpaRepository<Coder,String>{

    Optional<Coder> findByEmail(String email);


    @Override
    @RestResource(exported = false)
     <S extends Coder> S save(S entity) ;

  @Override
  @RestResource(exported = false)
  void delete(Coder entity);
    
}
