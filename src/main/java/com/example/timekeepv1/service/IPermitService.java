package com.example.timekeepv1.service;

import com.example.timekeepv1.dtos.PermitDto.PermitOutputDto;
import com.example.timekeepv1.dtos.PermitDto.PermitInputDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IPermitService {
    PermitOutputDto PermitOutputDto(PermitInputDto permit);
    List<PermitOutputDto> getAll();
    PermitOutputDto getPermitById(int id);
    PermitOutputDto updatePermit(PermitInputDto permit, int id);
    void deletePermit(int id);
}
