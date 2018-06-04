package com.jerry.poetry.repository;

import com.jerry.poetry.domain.shiro.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    @Query("select u from UserInfo u where u.username = :username")
    public UserInfo findByUsername(@Param("username") String username);
}