package com.example.timekeepv1.dtos.timework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TimeWorkBaseDto {

        public Time startTime;
        public Time stopTime;
        public Integer status;
}
