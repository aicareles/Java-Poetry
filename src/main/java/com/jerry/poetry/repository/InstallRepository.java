package com.jerry.poetry.repository;

import com.jerry.poetry.domain.Installer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallRepository extends JpaRepository<Installer, Integer> {
}
