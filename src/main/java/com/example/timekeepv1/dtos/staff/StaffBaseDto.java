package com.example.timekeepv1.dtos.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffBaseDto {
    public Integer id;
    public String name;
    public Boolean gender;
    public String email;
    public String adress;
    public Integer idPosition;
    public Integer idTimeWork;
}

