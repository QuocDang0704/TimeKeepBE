package com.example.timekeepv1.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffOutPutLoginDto {
    public Integer id;
    public String namePosition;
    public String name;
    public Boolean gender;
    public String email;
    public String adress;
    public Integer idPosition;
    public String image;
}
