package com.example.timekeepv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "time_keep", schema = "quoc_timekeep")
public class TimeKeepEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    @Basic
    @Column(name = "stop_time")
    private Date stopTime;
    @Basic
    @Column(name = "start_time_approved")
    private Date startTimeApproved;
    @Basic
    @Column(name = "stop_time_approved")
    private Date stopTimeApproved;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @Basic
    @Column(name = "id_staff")
    private Integer idStaff;
//    @Basic
//    @Column(name = "id_time_work")
//    private Integer idTimeWork;

}
