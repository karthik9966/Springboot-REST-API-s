package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.payload.JwtResponse;
import com.ttu.blogapplication.payload.LoginDto;
import com.ttu.blogapplication.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = {"login","signin"})
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto)
    {
        String token = loginService.loadByUsernameorEmail(loginDto);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
