package com.doori.hackerthon.repository;

import com.doori.hackerthon.entity.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
}
