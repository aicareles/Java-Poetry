package com.jerry.poetry.config.shiro;

import com.jerry.poetry.domain.shiro.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysPermissionInitRepository extends JpaRepository<SysPermission,Long> {

    List<SysPermission> findAll();
}
