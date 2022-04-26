package com.example.timekeepv1.controller;

import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import com.example.timekeepv1.service.ILoginRedisService;
import com.example.timekeepv1.service.IStaffService;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/redis")
public class DemoRedisLogin {
    private ILoginRedisService loginRedisService;

    public DemoRedisLogin(
            ILoginRedisService loginRedisService
    ) {
        this.loginRedisService = loginRedisService;
    }

    @GetMapping("/get-data-redis")
    public Map<String, StaffOutPutLoginDto> getDataRedis(){

//        loginRedisService.

        return loginRedisService.getAllStaffOutLogins();
    }
    @GetMapping("/get-data-redis{id}")
    public StaffOutPutLoginDto getDataRedisById(String id){
//        loginRedisService.saveAllStaffOutLogins(
//                Map.of("hong@gmail.com", new StaffOutPutLoginDto(36, "Trưởng Phòng", "Quỳnh Hảo",
//                                true, "hao@gmail.com", "Thanh Hóa", 1, "abc1"),
//                        "hao@gmail.com", new StaffOutPutLoginDto(38, "Trưởng Phòng", "phongTt",
//                                true, "dangquoc07044@gmail.com", "fpoly", 1, "abc2")
//                )
//                        );

        return loginRedisService.getOneStaffOutLogin(id);
    }
    @DeleteMapping
    public HttpStatus deleteRedis(String id){
        try {
            loginRedisService.deleteStaffOutLogin(id);
            return HttpStatus.OK;
        } catch (Exception ex){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
