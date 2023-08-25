package com.doori.hackerthon.repository;

import com.doori.hackerthon.entity.AdminGptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminGptRepository extends JpaRepository<AdminGptEntity, Long> {
}
