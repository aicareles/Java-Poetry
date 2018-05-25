package com.jerry.girl.repository.OAuth2;


import com.jerry.girl.domain.OAuth2.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityJPA extends JpaRepository<Authority, String> {
}
