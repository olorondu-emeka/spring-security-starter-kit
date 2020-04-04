package com.emeka.spring_security_starter_kit.controllers;

import com.emeka.spring_security_starter_kit.models.GeneralUser;
import com.emeka.spring_security_starter_kit.models.ResponseUser;
import com.emeka.spring_security_starter_kit.services.AdminUserDetailsService;
import com.emeka.spring_security_starter_kit.services.MyUserDetailsService;
import com.emeka.spring_security_starter_kit.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody GeneralUser generalUser) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(generalUser.getEmail(), generalUser.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        try{
            // handle use cases for USER and ADMIN roles
            final UserDetails userDetails = generalUser.getRole().equals("ADMIN") ?
                    adminUserDetailsService.loadUserByUsername(generalUser.getEmail())
                    : myUserDetailsService.loadUserByUsername(generalUser.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new ResponseUser(jwt, generalUser.getRole()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect role", e);
        }




    }
}
