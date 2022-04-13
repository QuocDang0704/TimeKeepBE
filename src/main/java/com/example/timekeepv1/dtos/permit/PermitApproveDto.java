package com.example.timekeepv1.dtos.permit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PermitApproveDto {
    private Integer id;
    private Integer status;
    private String note;
}
