package com.example.timekeepv1.service.impl;

import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import com.example.timekeepv1.service.ILoginRedisService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginRedisServiceImpl implements ILoginRedisService {

    private final String hashReference = "StaffOutPutLoginDto";

    @Resource(name = "redisStaffOutPutLogin")       // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, StaffOutPutLoginDto> hashOperations;

    @Override
    public boolean saveStaffOutLogin(StaffOutPutLoginDto staff) {
        //creates one record in Redis DB if record with that Id is not present
        //hashOperations.pu
        return hashOperations.putIfAbsent(hashReference, staff.getEmail(), staff);
    }

    @Override
    public StaffOutPutLoginDto getOneStaffOutLogin(String id) {
        return hashOperations.get(hashReference, id);
    }

    @Override
    public void updateStaffOutLogin(StaffOutPutLoginDto staff) {
        hashOperations.put(hashReference, staff.getEmail(), staff);
    }

    @Override
    public Map<String, StaffOutPutLoginDto> getAllStaffOutLogins() {
        return hashOperations.entries(hashReference);
    }

    @Override
    public void deleteStaffOutLogin(String id) {
        hashOperations.delete(hashReference, id);
    }

    @Override
    public void saveAllStaffOutLogins(Map<String, StaffOutPutLoginDto> map) {
        hashOperations.putAll(hashReference, map);
    }
}
