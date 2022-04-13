package com.example.timekeepv1.dtos.timekeep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TimeKeepOutputByMonthDto extends TimeKeepBaseDto {
    private Integer id;
    private Date startTime;
    private Date stopTime;
    private Date startTimeApproved;
    private Date stopTimeApproved;
    private Integer hourAreCounted;
    private String nameStaff;
    private String email;
    private Integer idTimeWork;
    private Integer idPosition;
    private String namePosition;
}
