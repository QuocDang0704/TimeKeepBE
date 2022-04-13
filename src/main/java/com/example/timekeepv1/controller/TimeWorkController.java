package com.example.timekeepv1.controller;


import com.example.timekeepv1.dtos.timekeep.TimeKeepOutputByMonthDto;
import com.example.timekeepv1.dtos.timework.TimeWorkBaseDto;
import com.example.timekeepv1.dtos.timework.TimeWorkOutputFullDto;
import com.example.timekeepv1.entity.TimeWorkEntity;
import com.example.timekeepv1.repository.ITimeWorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin("*")
@RequestMapping(value = "/apiTimeWorks")
public class TimeWorkController {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ITimeWorkRepository timeWorkRepository;

    @GetMapping("/timeworks")
    public ResponseEntity<List<TimeWorkOutputFullDto>> getAllTimeWorks() {
        try {
            List<TimeWorkOutputFullDto> timeworksDtos = new ArrayList<>();
            timeworksDtos = timeWorkRepository.findAll()
                    .stream()
                    .map(post -> modelMapper.map(post, TimeWorkOutputFullDto.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(timeworksDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/timeworks/{id}")
    public ResponseEntity<TimeWorkOutputFullDto> getTimeWorkById(@PathVariable("id") int id) {
        try {
            Optional<TimeWorkEntity> timeworkData = timeWorkRepository.findById(id);
            if (timeworkData.isPresent()) {
                TimeWorkOutputFullDto TimeWorkOutputFullDto = modelMapper.map(timeworkData.get(), TimeWorkOutputFullDto.class);
                return new ResponseEntity<>(TimeWorkOutputFullDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/timeworks")
    public ResponseEntity<TimeWorkOutputFullDto> createTimeWork(
            @RequestBody TimeWorkBaseDto timeWorkOutputDto) {
        try {
            TimeWorkEntity timeWorkEntity = timeWorkRepository.save(modelMapper.map(timeWorkOutputDto, TimeWorkEntity.class));
            TimeWorkOutputFullDto timeWorkOutputDto2 = modelMapper.map(timeWorkEntity, TimeWorkOutputFullDto.class);
            return new ResponseEntity<>(timeWorkOutputDto2, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/positions/{id}")
    public ResponseEntity<TimeWorkOutputFullDto> updatePosition(
            @PathVariable("id") int id,
            @RequestBody TimeWorkBaseDto timeWorkOutDto) {
        try {
            Optional<TimeWorkEntity> timeWorkData = timeWorkRepository.findById(id);
            if (timeWorkData.isPresent()) {
                TimeWorkEntity _timeWork = timeWorkData.get();
                _timeWork.setStartTime(timeWorkOutDto.getStartTime());
                _timeWork.setStopTime(timeWorkOutDto.getStopTime());
                _timeWork.setStatus(timeWorkOutDto.getStatus());


                TimeWorkOutputFullDto timeWorkOutDto2 = modelMapper
                        .map(timeWorkRepository.save(_timeWork), TimeWorkOutputFullDto.class);

                return new ResponseEntity<>(timeWorkOutDto2, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/timeworks")
    public ResponseEntity<HttpStatus> deleteTutorial(Integer id) {
        try {
            timeWorkRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
