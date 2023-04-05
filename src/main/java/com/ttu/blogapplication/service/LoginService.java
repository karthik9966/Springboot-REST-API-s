package com.ttu.blogapplication.service;

import com.ttu.blogapplication.payload.LoginDto;

public interface LoginService {

    public String loadByUsernameorEmail(LoginDto loginDto);
}
