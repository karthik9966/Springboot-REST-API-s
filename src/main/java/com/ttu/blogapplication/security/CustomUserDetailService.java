package com.ttu.blogapplication.security;

import com.ttu.blogapplication.model.User;
import com.ttu.blogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(username, username).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username not found");
        });
        System.out.println(user);
        List<GrantedAuthority> roles = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRole())
        ).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),roles);
    }
}
