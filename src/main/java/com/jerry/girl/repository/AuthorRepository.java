package com.jerry.girl.repository;

import com.jerry.girl.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Transactional(readOnly = true)
    @Query("select u from Author u where (u.author_id = :author_id) and (u.name like :author_name)")
    Optional<Author> getAuthorByIdAndName(@Param("author_id") int author_id, @Param("author_name") String author_name);

}
