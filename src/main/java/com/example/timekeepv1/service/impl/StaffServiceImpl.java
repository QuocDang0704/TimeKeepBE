package com.example.timekeepv1.service.impl;

import com.example.timekeepv1.dtos.position.PositionBaseDto;
import com.example.timekeepv1.dtos.staff.StaffBaseDto;
import com.example.timekeepv1.dtos.staff.StaffOutputFullDto;
import com.example.timekeepv1.entity.StaffEntity;
import com.example.timekeepv1.repository.IPositionRepository;
import com.example.timekeepv1.repository.IStaffRepository;
import com.example.timekeepv1.service.IStaffService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements IStaffService {
    private final ModelMapper modelMapper = new ModelMapper();
    IStaffRepository staffRepository;
    IPositionRepository positionRepository;

    public StaffServiceImpl(IStaffRepository staffRepository, IPositionRepository positionRepository) {
        this.staffRepository = staffRepository;
        this.positionRepository = positionRepository;
    }


    @Override
    public StaffOutputFullDto createStaff(StaffBaseDto staffBaseDto) {
        try {
            StaffEntity a1 = modelMapper.map(staffBaseDto, StaffEntity.class);
            a1.setId(null);
            StaffEntity staffEn = staffRepository.save(a1);
            StaffOutputFullDto staffDto = modelMapper.map(staffEn, StaffOutputFullDto.class);
            var position = positionRepository.findById(staffDto.getIdPosition());
            if (position.isPresent()) {
                PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
                staffDto.setNamePosition(positionBaseDto.getNamePosition());
            }
            return staffDto;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<StaffOutputFullDto> getAll() {

        try {
            List<StaffOutputFullDto> staffDtos = staffRepository.findAll()
                    .stream()
                    .map(post -> modelMapper.map(post, StaffOutputFullDto.class))
                    .collect(Collectors.toList());
            for (var x : staffDtos) {
                var position = positionRepository.findById(x.getIdPosition());
                if (position.isPresent()) {
                    PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
                    x.setNamePosition(positionBaseDto.getNamePosition());
                }
            }
            //staffDtos.forEach(i -> i.setNamePosition(positionRepository.getById(i.getId()).getNamePosition()));
            return staffDtos;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
//    @Override
//    public List<StaffOutputFullDto> getAll(Integer pageNumber, Integer pageSize) {
//        //
//        try {
//            Page<StaffEntity> staffPage = staffRepository.findAll(PageRequest.of(pageNumber, pageSize));
//            List<StaffOutputFullDto> staffDtos = staffPage.getContent()
//                    .stream()
//                    .map(post -> modelMapper.map(post, StaffOutputFullDto.class))
//                    .collect(Collectors.toList());
//            for (var x : staffDtos) {
//                var position = positionRepository.findById(x.getIdPosition());
//                if (position.isPresent()) {
//                    PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
//                    x.setNamePosition(positionBaseDto.getNamePosition());
//                }
//            }
//            //staffDtos.forEach(i -> i.setNamePosition(positionRepository.getById(i.getId()).getNamePosition()));
//            return staffDtos;
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }

    @Override
    public StaffOutputFullDto getStaffById(int id) {
        try {
            var staffData = staffRepository.findById(id);
            if (staffData.isPresent()) {
                StaffEntity en = staffData.get();
                StaffOutputFullDto staffDto = modelMapper.map(en, StaffOutputFullDto.class);
                staffDto.setNamePosition(positionRepository.getById(staffDto.getIdPosition()).getNamePosition());
                var position = positionRepository.findById(en.getIdPosition());
                if (position.isPresent()) {
                    PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
                    staffDto.setNamePosition(positionBaseDto.getNamePosition());
                    return staffDto;
                }
            }
            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public StaffOutputFullDto updateStaff(StaffBaseDto staffNew, int id) {
        try {
            StaffOutputFullDto staffOld = this.getStaffById(id);
            if (staffOld == null) return null;
            staffOld.setName(staffNew.getName());
            staffOld.setAdress(staffNew.getAdress());
            staffOld.setGender(staffNew.getGender());
            staffOld.setIdPosition(staffNew.getIdPosition());
            staffOld.setEmail(staffNew.getEmail());

            StaffEntity staffEn = staffRepository.save(modelMapper.map(staffOld, StaffEntity.class));
            StaffOutputFullDto staffDto = modelMapper.map(staffEn, StaffOutputFullDto.class);
            var position = positionRepository.findById(staffDto.getIdPosition());
            if (position.isPresent()) {
                PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
                staffDto.setNamePosition(positionBaseDto.getNamePosition());
            }
            return staffDto;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean deleteStaff(int id) {
        try {
            staffRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StaffOutputFullDto findStaffEntitiesByEmail(String email) {
        try {
            System.out.println("-------------aaaaaaaaaaaaaaaa-----------");
            var staffData = staffRepository.findByEmail(email);
            System.out.println("-------------bbbbbbbbbbbbbbb-----------");
            if (staffData.isPresent()) {
                StaffEntity en = staffData.get();
                StaffOutputFullDto outputFullDto = modelMapper.map(en, StaffOutputFullDto.class);
                outputFullDto.setNamePosition(positionRepository.getById(outputFullDto.getIdPosition()).getNamePosition());
                var position = positionRepository.findById(en.getIdPosition());
                if (position.isPresent()) {
                    PositionBaseDto positionBaseDto = modelMapper.map(position.get(), PositionBaseDto.class);
                    outputFullDto.setNamePosition(positionBaseDto.getNamePosition());
                    return outputFullDto;
                }
            }
            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
