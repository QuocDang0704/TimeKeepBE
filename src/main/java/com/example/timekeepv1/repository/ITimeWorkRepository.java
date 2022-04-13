package com.example.timekeepv1.repository;

import com.example.timekeepv1.entity.TimeWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ITimeWorkRepository extends JpaRepository<TimeWorkEntity, Integer> {
}
