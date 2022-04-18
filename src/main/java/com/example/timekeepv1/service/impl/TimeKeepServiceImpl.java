package com.example.timekeepv1.service.impl;

import com.example.timekeepv1.dtos.staff.StaffOutputFullDto;
import com.example.timekeepv1.dtos.timekeep.TestCheckInCheckOut;
import com.example.timekeepv1.dtos.timekeep.TimeKeepBaseDto;
import com.example.timekeepv1.dtos.timekeep.TimeKeepOutputByMonthDto;
import com.example.timekeepv1.dtos.timekeep.TimeKeepOutputFullDto;
import com.example.timekeepv1.entity.PositionEntity;
import com.example.timekeepv1.entity.StaffEntity;
import com.example.timekeepv1.entity.TimeKeepEntity;
import com.example.timekeepv1.entity.TimeWorkEntity;
import com.example.timekeepv1.repository.IPositionRepository;
import com.example.timekeepv1.repository.IStaffRepository;
import com.example.timekeepv1.repository.ITimeKeepRepository;
import com.example.timekeepv1.repository.ITimeWorkRepository;
import com.example.timekeepv1.service.IStaffService;
import com.example.timekeepv1.service.ITimeKeepService;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeKeepServiceImpl implements ITimeKeepService {
    ModelMapper modelMapper = new ModelMapper();

    ITimeKeepRepository timeKeepRepository;
    IStaffRepository staffRepository;
    IStaffService staffService;
    ITimeWorkRepository timeWorkRepository;
    IPositionRepository positionRepository;

    public TimeKeepServiceImpl(
            ITimeKeepRepository timeKeepRepository,
            IStaffRepository staffRepository,
            IStaffService staffService,
            ITimeWorkRepository timeWorkRepository,
            IPositionRepository positionRepository
    ) {
        this.timeKeepRepository = timeKeepRepository;
        this.staffRepository = staffRepository;
        this.staffService = staffService;
        this.timeWorkRepository = timeWorkRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public TimeKeepOutputFullDto createTimeKeep(TimeKeepBaseDto timeKeepBaseDto) {
        try {
            StaffEntity staff = staffRepository.getById(timeKeepBaseDto.getIdStaff());
            Optional<TimeWorkEntity> dataTimeWork = timeWorkRepository.findById(staff.getIdTimeWork());
            if (dataTimeWork.isPresent()) {
                TimeWorkEntity timeWork = dataTimeWork.get();

                TimeKeepEntity timeKeepEntity = new TimeKeepEntity();
                Date now = new Date();
                List<TimeKeepEntity> lst = timeKeepRepository.findAll();
                TimeKeepEntity timeKeep = lst.stream().filter(i -> (i.getIdStaff() == timeKeepBaseDto.getIdStaff()
                                && i.getStartTime().getDay() == now.getDay()
                                && i.getStartTime().getMonth() == now.getMonth()))
                        .findAny()
                        .orElse(null);
                if (timeKeep == null) {
                    TimeKeepOutputFullDto outputFullDto = modelMapper.map(timeKeepBaseDto, TimeKeepOutputFullDto.class);
                    outputFullDto.setStartTime(now);
                    if (now.getHours() <= timeWork.getStartTime().getHours()) {
                        now.setHours(timeWork.getStartTime().getHours());
                        now.setMinutes(timeWork.getStartTime().getMinutes());
                        now.setSeconds(timeWork.getStartTime().getSeconds());
                        outputFullDto.setStartTimeApproved(now);
                    } else {
                        outputFullDto.setStartTimeApproved(now);
                    }
                    outputFullDto.setStatus(false);
                    outputFullDto.setId(null);
                    timeKeepEntity = timeKeepRepository.save(modelMapper
                            .map(outputFullDto, TimeKeepEntity.class));
                } else {
                    return null;
                }
                var x = modelMapper.map(timeKeepEntity, TimeKeepOutputFullDto.class);
                StaffEntity staffTemp = staffRepository.getById(x.getIdStaff());
                x.setNameStaff(staffTemp.getName());
                x.setEmail(staffTemp.getEmail());
                x.setIdTimeWork(staffTemp.getIdTimeWork());

                return x;
            }
            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<TimeKeepOutputFullDto> getAll() {
        try {
            List<TimeKeepOutputFullDto> staffDtos = timeKeepRepository.findAllByStatusOrderByStartTimeApprovedDesc(true)
                    .stream()
                    .map(post -> modelMapper.map(post, TimeKeepOutputFullDto.class))
                    .collect(Collectors.toList());
            for (TimeKeepOutputFullDto x : staffDtos) {
                StaffEntity staff = staffRepository.getById(x.getIdStaff());
                //PositionEntity position = positionRepository.getById(staff.getIdPosition());
                x.setNameStaff(staff.getName());
                x.setEmail(staff.getEmail());
                x.setIdTimeWork(staff.getIdTimeWork());
                x.setIdPosition(staff.getIdPosition());
                //x.setNamePosition(position.getNamePosition());
            }
            //staffDtos.forEach(i -> i.setNamePosition(positionRepository.getById(i.getId()).getNamePosition()));
            return staffDtos;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public TimeKeepOutputFullDto getTimeKeepById(int id) {
        return null;
    }

    //    @Override
//    public TimeKeepOutputFullDto updateTimeKeep(TimeKeepBaseDto timeKeepBaseDto) {
//        try {
//            TimeKeepEntity timeKeepEntity = new TimeKeepEntity();
//            StaffOutputFullDto staffEntity = staffService.getStaffById(timeKeepBaseDto.getIdStaff());
//
//            Optional<TimeWorkEntity> dataTimeWork = timeWorkRepository.findById(staffEntity.getIdTimeWork());
//            if (dataTimeWork.isPresent()) {
//                TimeWorkEntity timeWork = dataTimeWork.get();
//
//                Date now = new Date();
//                List<TimeKeepEntity> lst = timeKeepRepository.findAll();
//                TimeKeepEntity timeKeep = lst.stream().filter(i -> (i.getIdStaff() == timeKeepBaseDto.getIdStaff()
//                                && i.getStartTime().getDay() == now.getDay())).findAny()
//                        .orElse(null);
//                if (timeKeep == null) {
//                    return null;
//                } else {
//                    if (timeKeep.getStatus() == true) {
//                        return null;
//                    }
//                    //TimeKeepOutputFullDto outputFullDto = modelMapper.map(timeKeepBaseDto, TimeKeepOutputFullDto.class);
//                    timeKeep.setStopTime(now);
//                    if (now.getHours() >= timeWork.getStopTime().getHours()) {
//                        now.setHours(timeWork.getStopTime().getHours());
//                        now.setMinutes(0);
//                        now.setSeconds(0);
//                        timeKeep.setStopTimeApproved(now);
//                    } else {
//                        timeKeep.setStopTimeApproved(now);
//                    }
//                    timeKeep.setStatus(true);
//                    timeKeepEntity = timeKeepRepository.save(timeKeep);
//                }
//                var x = modelMapper.map(timeKeepEntity, TimeKeepOutputFullDto.class);
//                StaffEntity staff = staffRepository.getById(x.getIdStaff());
//                x.setNameStaff(staff.getName());
//                x.setEmail(staff.getEmail());
//                x.setIdTimeWork(staff.getIdTimeWork());
//                return x;
//            }
//            return null;
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }
    @Override
    public TimeKeepOutputFullDto updateTimeKeep(TimeKeepBaseDto timeKeepBaseDto) {
        try {
            StaffEntity staff = staffRepository.getById(timeKeepBaseDto.getIdStaff());
            TimeWorkEntity timeWork = timeWorkRepository.getById(staff.getIdTimeWork());
            PositionEntity position = positionRepository.getById(staff.getIdPosition());
            List<TimeKeepEntity> lst = timeKeepRepository
                    .findByIdStaffAndAndStatus(timeKeepBaseDto.getIdStaff(), false);
            if (lst.size() == 0 || lst.isEmpty()) return null;
            for (TimeKeepEntity x : lst) {
                Date now = new Date();
                Date now2 = new Date();
                if (x.getStartTimeApproved().getDay() == now.getDay()
                        && x.getStartTime().getMonth() == now.getMonth()) {
                    x.setStatus(true);
//                    x.setStopTime(now);
                    if (now.getHours() >= timeWork.getStopTime().getHours()) {
                        now.setHours(timeWork.getStopTime().getHours());
                        now.setMinutes(timeWork.getStopTime().getMinutes());
                        now.setSeconds(timeWork.getStopTime().getSeconds());
                        x.setStopTimeApproved(timeWork.getStopTime());


                        if (x.getStartTime().getHours()>=timeWork.getStopTime().getHours()){
                            x.setStopTimeApproved(now2);
                        }
                    } else {
                        x.setStopTimeApproved(now);
                    }
                    x.setStopTime(now2);
                    TimeKeepEntity temp = timeKeepRepository.save(x);
                    TimeKeepOutputFullDto outputFullDto = modelMapper.map(temp, TimeKeepOutputFullDto.class);
                    outputFullDto.setEmail(staff.getEmail());
                    outputFullDto.setNameStaff(staff.getName());
                    outputFullDto.setIdTimeWork(timeWork.getId());
                    outputFullDto.setIdPosition(position.getId());
                    outputFullDto.setNamePosition(position.getNamePosition());
                    return outputFullDto;
                }
            }

            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean deleteTimeKeep(int id) {
        try {
            timeKeepRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TimeKeepOutputFullDto findByStartTime(Date starttime, Integer id) {
//        TimeKeepEntity timeKeepEntity = timeKeepRepository.findByStartTime(starttime, id);
//        return modelMapper.map(timeKeepEntity, TimeKeepOutputFullDto.class);
        return null;
    }

    @Override
    public List<TimeKeepOutputByMonthDto> getTimeKeepToMonth(Integer idStaff, Integer month) {
        try {
            StaffEntity staff = staffRepository.getById(idStaff);
            PositionEntity position = positionRepository.getById(idStaff);

            List<TimeKeepEntity> lst = timeKeepRepository.getTimeKeepToMonth(idStaff, month);
            if (lst.size()==0 || lst.isEmpty()) return null;
            List<TimeKeepOutputByMonthDto> lstOut = lst
                    .stream()
                    .map(post -> modelMapper.map(post, TimeKeepOutputByMonthDto.class))
                    .collect(Collectors.toList());
            for (TimeKeepOutputByMonthDto x : lstOut) {
                if (x.getStatus()) {
                    long diff = x.getStopTimeApproved().getTime() - x.getStartTimeApproved().getTime();
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;

                    if (diffMinutes >= 30) {
                        diffHours++;
                    }
                    x.setEmail(staff.getEmail());
                    x.setNameStaff(staff.getName());
                    x.setIdPosition(staff.getIdPosition());
                    x.setIdTimeWork(staff.getIdTimeWork());
                    int hours = (int) diffHours;
                    x.setHourAreCounted(hours);
                    x.setNamePosition(position.getNamePosition());
                }
            }
            return lstOut;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public TimeKeepEntity testCheckInCheckOut(TestCheckInCheckOut input){
        try {
            Date now = new Date();
            TimeKeepEntity timeKeep = new TimeKeepEntity();
            List<TimeKeepEntity> lst = timeKeepRepository
                    .getTimeKeepToMonthAnDay(input.getIdStaff(), input.getMonth(), input.getDay());
            if (lst.size() == 0 || lst.isEmpty()) return null;
            return lst.get(0);

        }catch (Exception e){
            return null;
        }
    };
    @Override
    public List<TimeKeepOutputByMonthDto> findAllByIdStaff(Integer idStaff) {
        try {
            StaffEntity staff = staffRepository.getById(idStaff);
            PositionEntity position = positionRepository.getById(idStaff);

            List<TimeKeepEntity> lst = timeKeepRepository.findAllByIdStaff(idStaff);
            if (lst.size()==0 || lst.isEmpty()) return null;
            List<TimeKeepOutputByMonthDto> lstOut = lst
                    .stream()
                    .map(post -> modelMapper.map(post, TimeKeepOutputByMonthDto.class))
                    .collect(Collectors.toList());
            for (TimeKeepOutputByMonthDto x : lstOut) {
                if (x.getStatus()) {
                    long diff = x.getStopTimeApproved().getTime() - x.getStartTimeApproved().getTime();
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;

                    if (diffMinutes >= 30) {
                        diffHours++;
                    }
                    x.setEmail(staff.getEmail());
                    x.setNameStaff(staff.getName());
                    x.setIdPosition(staff.getIdPosition());
                    x.setIdTimeWork(staff.getIdTimeWork());
                    int hours = (int) diffHours;
                    x.setHourAreCounted(hours);
                    x.setNamePosition(position.getNamePosition());
                }
            }
            return lstOut;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }



//    public void HandlingMismatches (){
//        modelMapper.typeMap(TimeKeepEntity.class, TimeKeepOutputByMonthDto.class)
//                .addMappings(mapper -> {
//            mapper.map(src -> Math.round(
//                    src.getStartTimeApproved()
//                            .getHours()
//                        -
//                    src.getStopTimeApproved()
//                            .getHours()),
//                    TimeKeepOutputByMonthDto::setHourAreCounted);
//        });
//    }

}
