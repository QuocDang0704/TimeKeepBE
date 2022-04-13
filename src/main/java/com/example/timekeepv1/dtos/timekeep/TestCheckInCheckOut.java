package com.example.timekeepv1.dtos.timekeep;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestCheckInCheckOut {
    private Integer idStaff;
    private Integer month;
    private Integer day;
}
