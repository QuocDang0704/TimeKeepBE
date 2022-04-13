package com.example.timekeepv1.dtos.permit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PermitInputDto {
    private Integer idTimeKeep;
    private String reason;

    private String timeIn;
    private String timeOut;
}
