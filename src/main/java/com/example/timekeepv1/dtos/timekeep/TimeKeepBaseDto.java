package com.example.timekeepv1.dtos.timekeep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TimeKeepBaseDto {
    private Boolean status;
    private Integer idStaff;
}
