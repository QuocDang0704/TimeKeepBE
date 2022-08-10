package com.example.timekeepv1.controller;


import com.example.timekeepv1.dtos.timekeep.TestCheckInCheckOut;
import com.example.timekeepv1.dtos.timekeep.TimeKeepBaseDto;
import com.example.timekeepv1.dtos.timekeep.TimeKeepOutputByMonthDto;
import com.example.timekeepv1.dtos.timekeep.TimeKeepOutputFullDto;
import com.example.timekeepv1.entity.TimeKeepEntity;
import com.example.timekeepv1.repository.ITimeKeepRepository;
import com.example.timekeepv1.service.ITimeKeepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeKeeps")
@CrossOrigin("*")
public class TimeKeepController {
    private final ITimeKeepService timeKeepService;
    private final ITimeKeepRepository timeKeepRepository;

    public TimeKeepController(ITimeKeepService timeKeepService, ITimeKeepRepository timeKeepRepository) {

        this.timeKeepService = timeKeepService;
        this.timeKeepRepository = timeKeepRepository;
    }

    @GetMapping("/timekeep")
    public ResponseEntity<List<TimeKeepOutputFullDto>> getAllTimeKeep() {
        List<TimeKeepOutputFullDto> timekeepDtos = timeKeepService.getAll();
        if (timekeepDtos == null) new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(timekeepDtos, (!timekeepDtos.isEmpty()) ? (HttpStatus.OK) : (HttpStatus.NOT_FOUND));
    }

    @PostMapping("/checkin-checkout")
    public ResponseEntity<TimeKeepOutputFullDto> checkin_checkout(
            @RequestBody TimeKeepBaseDto timeKeepBaseDto){
        TimeKeepOutputFullDto outputFullDto;
        if (timeKeepBaseDto.getStatus()){
            outputFullDto = timeKeepService.createTimeKeep(timeKeepBaseDto);
        }else {
            outputFullDto = timeKeepService.updateTimeKeep(timeKeepBaseDto);
        }
        if (outputFullDto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputFullDto, HttpStatus.OK);
    }

    @GetMapping("/timekeep/history")
    public ResponseEntity<List<TimeKeepOutputByMonthDto>> getTimeKeepToMonth(
            Integer idStaff,
            Integer month){
        try {
            if (month==null){
                List<TimeKeepOutputByMonthDto> timekeepDtos = timeKeepService.findAllByIdStaff(idStaff);
                if (timekeepDtos == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(timekeepDtos, (!timekeepDtos.isEmpty()) ? (HttpStatus.OK) : (HttpStatus.NOT_FOUND));
            }
            List<TimeKeepOutputByMonthDto> lst = timeKeepService.getTimeKeepToMonth(idStaff, month);
            if (lst.size()==0 || lst.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(lst, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/timekeep/delete")
    public ResponseEntity<HttpStatus> deleteTutorial(Integer idDete) {
        if (timeKeepService.deleteTimeKeep(idDete)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/timekeep/test")
    public ResponseEntity<TimeKeepEntity> test(
            @RequestBody TestCheckInCheckOut input) {
        try {
            System.out.println(input.getDay() +"     "+input.getMonth());
            TimeKeepEntity timeKeep = timeKeepService.testCheckInCheckOut(input);
            if (timeKeep==null){
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            System.out.println(timeKeep.getStatus());
            return new ResponseEntity<>(timeKeep, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/timekeep/staff")
    public ResponseEntity<List<TimeKeepOutputByMonthDto>> findAllByIdStaff(Integer idStaff) {
        List<TimeKeepOutputByMonthDto> timekeepDtos = timeKeepService.findAllByIdStaff(idStaff);
        if (timekeepDtos == null) new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(timekeepDtos, (!timekeepDtos.isEmpty()) ? (HttpStatus.OK) : (HttpStatus.NOT_FOUND));
    }

}
