package com.jerry.poetry.repository;

import com.jerry.poetry.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Integer> {
//    @Transactional(readOnly = true)
//    UploadFile getUploadFilesById(@Param("id")int id);

    @Query("select u from Game u where (u.nick_name = :nick_name)")
    List<Game> findAllByNick_name(@Param("nick_name")String nick_name);

    @Query("select u from Game u where (u.day = :day)")
    List<Game> findAllByDay(@Param("day")String day);
}
