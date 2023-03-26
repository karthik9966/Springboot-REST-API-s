package com.ttu.blogapplication.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("abc"));
        System.out.println(passwordEncoder.encode("def"));
        System.out.println(passwordEncoder.encode("ghi"));
    }
}
