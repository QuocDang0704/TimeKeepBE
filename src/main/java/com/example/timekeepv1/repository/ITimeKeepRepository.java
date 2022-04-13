package com.example.timekeepv1.repository;

import com.example.timekeepv1.entity.TimeKeepEntity;
import io.swagger.models.auth.In;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@EnableJpaRepositories
public interface ITimeKeepRepository extends JpaRepository<TimeKeepEntity, Integer> {

    List<TimeKeepEntity> findAllByIdStaff(Integer idStaff);
    List<TimeKeepEntity> findByIdStaffAndAndStatus(Integer idStaff, Boolean status);
    List<TimeKeepEntity> findAllByStatusOrderByStartTimeApprovedDesc(Boolean status);


    @Query(value = "select * from time_keep where " +
            "MONTH(start_time) = :month " +
            "and id_staff = :id", nativeQuery = true)
    List<TimeKeepEntity> getTimeKeepToMonth(
            @Param("id") Integer id,
            @Param("month") Integer month);

    @Query(value = "select * from time_keep where " +
            "MONTH(start_time) = :month and DAY(start_time) =:day " +
            "and id_staff = :id", nativeQuery = true)
    List<TimeKeepEntity> getTimeKeepToMonthAnDay(
            @Param("id") Integer id,
            @Param("month") Integer month,
            @Param("day") Integer day);
}
