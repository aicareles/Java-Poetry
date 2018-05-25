package com.jerry.girl.repository;

import com.jerry.girl.domain.Poetry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PoetryRepository extends JpaRepository<Poetry,Integer> {

//    @Transactional(readOnly = true)
//    @Query("select u from Poetry u where (u.author like CONCAT('%',:keyword,'%') or u.title like CONCAT('%',:keyword,'%') or u.content like CONCAT('%',:keyword,'%')) order by u.author")
//    Page<Poetry> findAllByKey(@Param("keyword")String keyword, Pageable pageable);
    Page<Poetry> findByAuthorContaining(String keyword, Pageable pageable);
    Page<Poetry> findByTitleContaining(String keyword, Pageable pageable);
    Page<Poetry> findByContentContaining(String keyword, Pageable pageable);
}
