package com.example.timekeepv1.service;

import com.example.timekeepv1.dtos.staff.StaffBaseDto;
import com.example.timekeepv1.dtos.staff.StaffOutputFullDto;


import java.util.List;

public interface IStaffService {
    StaffOutputFullDto createStaff(StaffBaseDto staff);
    List<StaffOutputFullDto> getAll();
    StaffOutputFullDto getStaffById(int id);
    StaffOutputFullDto updateStaff(StaffBaseDto staff, int id);
    boolean deleteStaff(int id);
    StaffOutputFullDto findStaffEntitiesByEmail(String email);
}
