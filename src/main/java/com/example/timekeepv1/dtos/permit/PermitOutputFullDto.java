package com.example.timekeepv1.dtos.permit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PermitOutputFullDto {
    private Integer id;
    private Integer status;
    private String note;
    private Integer idTimeKeep;
    private String reason;
    private String timeIn;
    private String timeOut;
    private String nameStaff;
    private String email;
    private Boolean isCancel;
}
