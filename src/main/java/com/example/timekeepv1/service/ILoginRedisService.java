package com.example.timekeepv1.service;

import com.example.timekeepv1.auth.StaffOutPutLoginDto;

import java.util.Map;

public interface ILoginRedisService {
    boolean saveStaffOutLogin(StaffOutPutLoginDto staff);
    StaffOutPutLoginDto getOneStaffOutLogin(String id);
    void updateStaffOutLogin(StaffOutPutLoginDto staff);
    Map<String, StaffOutPutLoginDto> getAllStaffOutLogins();
    void deleteStaffOutLogin(String id);
    void saveAllStaffOutLogins(Map<String, StaffOutPutLoginDto> map);
}
