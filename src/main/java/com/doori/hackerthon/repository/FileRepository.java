package com.doori.hackerthon.repository;

import com.doori.hackerthon.entity.InputFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<InputFile, Long> {
}
