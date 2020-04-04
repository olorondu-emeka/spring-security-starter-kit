package com.emeka.spring_security_starter_kit.services;

import com.emeka.blogspringboot.models.Author;
import com.emeka.blogspringboot.models.MyUserDetails;
import com.emeka.blogspringboot.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Author> generalUser = authorRepository.findByEmail(email);
        generalUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", email)));
        return generalUser.map(MyUserDetails::new).get();
    }
}
