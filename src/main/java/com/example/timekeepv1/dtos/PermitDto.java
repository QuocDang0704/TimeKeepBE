package com.example.timekeepv1.dtos;

public class PermitDto {
    public class PermitBaseDto{
        public int id;
        public Integer status;
        public String reason;
    }
    public class PermitOutputDto extends PermitBaseDto{}
    public class PermitInputDto extends PermitBaseDto{}
}
