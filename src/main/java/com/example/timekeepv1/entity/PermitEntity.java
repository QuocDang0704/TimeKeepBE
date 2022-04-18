package com.example.timekeepv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "permit", schema = "quoc_timekeep")
public class PermitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "reason")
    private String reason;
    @Basic
    @Column(name = "id_time_keep")
    private Integer idTimeKeep;
    @Basic
    @Column(name = "note")
    private String note;
    @Basic
    @Column(name = "time_in")
    private Date timeIn;
    @Basic
    @Column(name = "time_out")
    private Date timeOut;
    @Basic
    @Column(name = "is_cancel")
    private Boolean isCancel;
}
