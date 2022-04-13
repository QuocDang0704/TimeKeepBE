package com.example.timekeepv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "time_work", schema = "quoc_timekeep")
public class TimeWorkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    @Basic
    @Column(name = "stop_time")
    private Time stopTime;
    @Basic
    @Column(name = "status")
    private Integer status;
}
