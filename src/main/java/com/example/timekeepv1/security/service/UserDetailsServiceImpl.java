package com.example.timekeepv1.security.service;

import com.example.timekeepv1.entity.StaffEntity;
import com.example.timekeepv1.repository.IStaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IStaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.build(staffRepository.findByEmail(email)
                .orElseThrow(() -> {throw new IllegalArgumentException("khong tim thay email");}));
    }
}
