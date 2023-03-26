package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.payload.JwtResponse;
import com.ttu.blogapplication.payload.LoginDto;
import com.ttu.blogapplication.repository.UserRepository;
import com.ttu.blogapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = {"login","signin"})
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto)
    {
        String token = loginService.loadByUsernameorEmail(loginDto);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
