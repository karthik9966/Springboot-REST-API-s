package com.ttu.blogapplication.service.impl;

import com.ttu.blogapplication.payload.LoginDto;
import com.ttu.blogapplication.repository.UserRepository;
import com.ttu.blogapplication.service.LoginService;
import com.ttu.blogapplication.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String loadByUsernameorEmail(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtTokenProvider.generateToken(authenticate);

        return token;
    }
}
