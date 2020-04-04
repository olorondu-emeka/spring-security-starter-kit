package com.emeka.spring_security_starter_kit.services;

import com.emeka.blogspringboot.models.Admin;
import com.emeka.blogspringboot.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public String createAdmin(Admin admin) {
        admin.setRole("ADMIN");
        adminRepository.save(admin);
        return "Admin created successfully";
    }
}
