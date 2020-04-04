package com.emeka.spring_security_starter_kit.services;

import com.emeka.blogspringboot.models.Admin;
import com.emeka.blogspringboot.models.AdminUserDetails;
import com.emeka.blogspringboot.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserDetailsService  implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        admin.orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", email)));
        return admin.map(AdminUserDetails::new).get();
    }
}
