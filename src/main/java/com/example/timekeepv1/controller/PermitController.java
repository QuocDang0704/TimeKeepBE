package com.example.timekeepv1.controller;


import com.example.timekeepv1.dtos.permit.PermitApproveDto;
import com.example.timekeepv1.dtos.permit.PermitInputDto;
import com.example.timekeepv1.dtos.permit.PermitOutputFullDto;
import com.example.timekeepv1.entity.PermitEntity;
import com.example.timekeepv1.entity.StaffEntity;
import com.example.timekeepv1.entity.TimeKeepEntity;
import com.example.timekeepv1.repository.IPermitRepository;
import com.example.timekeepv1.repository.IStaffRepository;
import com.example.timekeepv1.repository.ITimeKeepRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping( "/permit")
public class PermitController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final IPermitRepository permitRepository;
    private final ITimeKeepRepository timeKeepRepository;
    private final IStaffRepository staffRepository;

    public PermitController(IPermitRepository permitRepository
            , ITimeKeepRepository timeKeepRepository
            ,IStaffRepository staffRepository) {
        this.permitRepository = permitRepository;
        this.timeKeepRepository = timeKeepRepository;
        this.staffRepository = staffRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<PermitEntity> createPermit(
            @RequestBody PermitInputDto input){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeIn = sdf.parse(input.getTimeIn());
            Date timeOut = sdf.parse(input.getTimeOut());
            PermitEntity permitTemp = modelMapper.map(input, PermitEntity.class);
            permitTemp.setNote(null);
            permitTemp.setStatus(0);
            permitTemp.setId(null);
            permitTemp.setTimeOut(timeOut);
            permitTemp.setTimeIn(timeIn);
            permitTemp.setIsCancel(false);
            PermitEntity permit = permitRepository.save(permitTemp);
            return new ResponseEntity<>(permit, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/approve")
    public ResponseEntity<PermitEntity> ApprovePermit(
            @RequestBody PermitApproveDto input){
        try {
            if (input.getStatus()==0){
                PermitEntity permit = permitRepository.getById(input.getId());
                permit.setIsCancel(true);
                permit.setNote(input.getNote());
                return new ResponseEntity<>(permitRepository.save(permit), HttpStatus.OK);
            }
            PermitEntity permit = permitRepository.getById(input.getId());
            permit.setNote(input.getNote());
            permit.setStatus(1);
            TimeKeepEntity timeKeep = timeKeepRepository.getById(permit.getIdTimeKeep());
            timeKeep.setStopTimeApproved(permit.getTimeOut());
            timeKeep.setStartTimeApproved(permit.getTimeIn());
            timeKeepRepository.save(timeKeep);
            PermitEntity permit2 = permitRepository.save(permit);
            return new ResponseEntity<>(permit2, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/permits")
    public ResponseEntity<List<PermitOutputFullDto>> getAll() {
        try {
            List<PermitEntity> lst = permitRepository.findAll();
            if (lst.size() ==0 || lst.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            List<PermitOutputFullDto> lstOut = lst
                    .stream()
                    .map(post -> modelMapper.map(post, PermitOutputFullDto.class))
                    .collect(Collectors.toList());
            for (PermitOutputFullDto x: lstOut) {
                System.out.println("3");
                TimeKeepEntity timeKeep = timeKeepRepository.getById(x.getIdTimeKeep());
                StaffEntity staff = staffRepository.getById(timeKeep.getIdStaff());
                x.setEmail(staff.getEmail());
                x.setNameStaff(staff.getName());
            }
            return new ResponseEntity<>(lstOut, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/permits/status")
    public ResponseEntity<List<PermitOutputFullDto>> getAllByStatus(Integer status) {
        try {
            List<PermitEntity> lst = permitRepository.findAllByStatus(status);
            if (lst.size() ==0 || lst.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            List<PermitOutputFullDto> lstOut = lst
                    .stream()
                    .map(post -> modelMapper.map(post, PermitOutputFullDto.class))
                    .collect(Collectors.toList());
            for (PermitOutputFullDto x: lstOut) {
                System.out.println("3");
                TimeKeepEntity timeKeep = timeKeepRepository.getById(x.getIdTimeKeep());
                StaffEntity staff = staffRepository.getById(timeKeep.getIdStaff());
                x.setEmail(staff.getEmail());
                x.setNameStaff(staff.getName());
            }
            return new ResponseEntity<>(lstOut, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
