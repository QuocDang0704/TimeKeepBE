package com.example.timekeepv1.dtos.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffOutputFullDto extends StaffBaseDto{
    public Integer id;
    public String namePosition;
}
