package com.example.timekeepv1.controller;

import com.example.timekeepv1.dtos.staff.StaffBaseDto;
import com.example.timekeepv1.dtos.staff.StaffOutputFullDto;
import com.example.timekeepv1.service.impl.StaffServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {
    private final StaffServiceImpl staffService;

    public StaffController(StaffServiceImpl staffService) {
        this.staffService = staffService;
    }

//    @GetMapping("/staffs")
//    public ResponseEntity<List<StaffOutputFullDto>> getAllPositions() {
//        List<StaffOutputFullDto> staffDtos = staffService.getAll();
//        if (staffDtos == null) new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(staffDtos, (!staffDtos.isEmpty()) ? (HttpStatus.OK) : (HttpStatus.NOT_FOUND));
//    }
    @GetMapping("/staffs")
    public ResponseEntity<List<StaffOutputFullDto>> getAllPositions() {
        List<StaffOutputFullDto> staffDtos = staffService.getAll();
        if (staffDtos == null) new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(staffDtos, (!staffDtos.isEmpty()) ? (HttpStatus.OK) : (HttpStatus.NOT_FOUND));
    }

    @GetMapping("/staffs/{id}")
    public ResponseEntity<StaffOutputFullDto> getPositionById(@PathVariable("id") int id) {
        StaffOutputFullDto staffInputDto = staffService.getStaffById(id);
        if (staffInputDto == null) new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(staffInputDto, HttpStatus.OK);
    }

//    @PutMapping("/staffs/{id}")
//    public ResponseEntity<StaffOutputFullDto> updatePosition(
//            @PathVariable("id") int id,
//            @RequestBody StaffBaseDto staffBaseDto
//    ) {
//        StaffOutputFullDto staffInputDto = staffService.updateStaff(staffBaseDto, id);
//        if (staffInputDto == null) {new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
//        return new ResponseEntity<>(staffInputDto, HttpStatus.OK);
//    }
    @PutMapping("/staffs")
    public ResponseEntity<StaffOutputFullDto> updatePosition(
//            Integer id,
            @RequestBody StaffBaseDto staffBaseDto
    ) {
        StaffOutputFullDto staffInputDto = staffService.updateStaff(staffBaseDto, staffBaseDto.getId());
        if (staffInputDto == null) {new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(staffInputDto, HttpStatus.OK);
    }

    @PostMapping("/staff")
    public ResponseEntity<StaffOutputFullDto> createPosition(
            @RequestBody StaffBaseDto staffBaseDto
    ) {
        StaffOutputFullDto staffInputDto = staffService.createStaff(staffBaseDto);
        if (staffInputDto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(staffInputDto, HttpStatus.OK);
    }

    @DeleteMapping("/staffs/delete")
    public ResponseEntity<HttpStatus> deleteTutorial(Integer idStaff) {
        if (staffService.deleteStaff(idStaff)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/staffs/findby{email}")
    public ResponseEntity<StaffOutputFullDto> getPositionById(@PathVariable("email") String email) {
        StaffOutputFullDto staffInputDto = staffService.findStaffEntitiesByEmail(email);
        if (staffInputDto == null) new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(staffInputDto, HttpStatus.OK);
    }
}
