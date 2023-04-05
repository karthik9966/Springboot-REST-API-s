package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.payload.RegisterDto;
import com.ttu.blogapplication.service.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(value = {"register","signup"})
    public String registerUser(@RequestBody RegisterDto registerDto)
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return registerService.registerUser(registerDto);
    }
}
