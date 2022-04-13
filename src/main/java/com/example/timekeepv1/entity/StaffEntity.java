package com.example.timekeepv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "staff", schema = "quoc_timekeep")
public class StaffEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "gender")
    private Boolean gender;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "adress")
    private String adress;
    @Basic
    @Column(name = "id_position")
    private Integer idPosition;
    @Basic
    @Column(name = "id_time_work")
    private Integer idTimeWork;

}
