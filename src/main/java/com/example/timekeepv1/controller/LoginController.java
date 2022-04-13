package com.example.timekeepv1.controller;

import com.example.timekeepv1.auth.GoogleSocail;
import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import com.example.timekeepv1.dtos.staff.StaffOutputFullDto;
import com.example.timekeepv1.service.IStaffService;
import io.swagger.models.Response;
import jdk.jshell.Snippet;
import org.aspectj.bridge.Message;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final ModelMapper modelMapper = new ModelMapper();
    IStaffService staffService;

    public LoginController(IStaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/socialmediaData")
    public ResponseEntity<StaffOutPutLoginDto> SocialmediaData(
            @RequestBody GoogleSocail user) throws Exception {
        try {
            var output =  staffService.findStaffEntitiesByEmail(user.getEmail());
            if (output == null){
                throw new Exception("khong tim thay user theo email");
                //return null;
            }
            var output2 = modelMapper.map(output, StaffOutPutLoginDto.class);
            output2.setImage(user.getImage());
            return new ResponseEntity<>(output2, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage()+"");
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
