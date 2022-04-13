package com.example.timekeepv1.controller;

import com.example.timekeepv1.dtos.position.PositionBaseDto;
import com.example.timekeepv1.dtos.position.PositionOutputFullDto;
import com.example.timekeepv1.entity.PositionEntity;
import com.example.timekeepv1.repository.IPositionRepository;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping( "/apiPositions")
public class PositionController {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    IPositionRepository positionRepository;

    @GetMapping("/positions")
    public ResponseEntity<List<PositionOutputFullDto>> getAllPositions() {
        try {
            List<PositionOutputFullDto> PositionOutputFullDtos = positionRepository.findAll()
                    .stream()
                    .map(post -> modelMapper.map(post, PositionOutputFullDto.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(PositionOutputFullDtos, PositionOutputFullDtos.isEmpty()?HttpStatus.NOT_FOUND:HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<PositionOutputFullDto> getPositionById(@PathVariable("id") int id) {
        try {
            Optional<PositionEntity> position = positionRepository.findById(id);
            if (position.isPresent()) {
                PositionOutputFullDto PositionOutputFullDto = modelMapper.map(position.get(), PositionOutputFullDto.class);
                return new ResponseEntity<>(PositionOutputFullDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/positions")
    public ResponseEntity<PositionOutputFullDto> createPosition(@RequestBody PositionBaseDto positionOutDto) {
        try {
            PositionEntity positionEntity = positionRepository
                    .save(modelMapper.map(positionOutDto, PositionEntity.class));
            PositionOutputFullDto positionOutDto2 = modelMapper.map(positionEntity, PositionOutputFullDto.class);
            return new ResponseEntity<>(positionOutDto2, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/positions/{id}")
    public ResponseEntity<PositionOutputFullDto> updatePosition(
            @PathVariable("id") int id,
            @RequestBody PositionBaseDto positionOutDto) {
        try {
            Optional<PositionEntity> positionData = positionRepository.findById(id);
            if (positionData.isPresent()) {
                PositionEntity _position = positionData.get();
                _position.setNamePosition(positionOutDto.getNamePosition());
                _position.setRole(positionOutDto.getRole());

                PositionOutputFullDto positionOutDto2 = modelMapper
                        .map(positionRepository.save(_position), PositionOutputFullDto.class);

                return new ResponseEntity<>(positionOutDto2, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/positions")
    public ResponseEntity<HttpStatus> deleteTutorial(Integer id) {
        try {
            positionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
