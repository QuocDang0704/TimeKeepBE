package com.example.timekeepv1.repository;

import com.example.timekeepv1.entity.StaffEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface IStaffRepository extends JpaRepository<StaffEntity, Integer> {
    Optional<StaffEntity> findByEmail(String email);
    Page<StaffEntity> findAll(Pageable pageable);

}
