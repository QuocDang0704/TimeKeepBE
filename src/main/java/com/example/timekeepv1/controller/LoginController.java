package com.example.timekeepv1.controller;

import com.example.timekeepv1.auth.GoogleSocail;
import com.example.timekeepv1.auth.JwtResponse;
import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import com.example.timekeepv1.security.jwt.JwtUtils;
import com.example.timekeepv1.security.service.UserDetailsImpl;
import com.example.timekeepv1.service.ILoginRedisService;
import com.example.timekeepv1.service.IStaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    private final ModelMapper modelMapper = new ModelMapper();
    IStaffService staffService;
    ILoginRedisService loginRedisService;

    public LoginController(IStaffService staffService,
                           ILoginRedisService loginRedisService
                           ) {
        this.staffService = staffService;
        this.loginRedisService = loginRedisService;
    }

    @PostMapping("/socialmediaData")
    public ResponseEntity<StaffOutPutLoginDto> SocialmediaData(
            @RequestBody GoogleSocail user) throws Exception {
        try {
            if (checkDataRedis(user.getEmail())!=null){
                return new ResponseEntity<>(checkDataRedis(user.getEmail()), HttpStatus.OK);
            }
            var output =  staffService.findStaffEntitiesByEmail(user.getEmail());
            if (output == null){
                throw new Exception("khong tim thay user theo email");
                //return null;
            }
            var output2 = modelMapper.map(output, StaffOutPutLoginDto.class);
            output2.setImage(user.getImage());

            if (insertStaffRedis(output2)==null){
                new Throwable("không thể insert staff to redis");
            }
            return new ResponseEntity<>(output2, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage()+"");
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    private StaffOutPutLoginDto checkDataRedis(String email){
        try {
            StaffOutPutLoginDto staff = loginRedisService.getOneStaffOutLogin(email);
            if (null==staff){
                return null;
            }
            System.out.println("đã đi qua đây hihi về get");
            return staff;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private StaffOutPutLoginDto insertStaffRedis(StaffOutPutLoginDto staff){
        try {
            if (loginRedisService.saveStaffOutLogin(staff)){
                System.out.println("đã đi qua đây hihi về insert");
                return staff;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody GoogleSocail loginRequest) {
        System.out.println(encoder.encode("123"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getImage()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,"Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

}
