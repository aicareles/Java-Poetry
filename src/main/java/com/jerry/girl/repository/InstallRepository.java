package com.jerry.girl.repository;

import com.jerry.girl.domain.Installer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallRepository extends JpaRepository<Installer, Integer> {
}
