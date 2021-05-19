package com.jerry.poetry.repository;

import com.jerry.poetry.domain.shiro.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
