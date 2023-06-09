package com.ttu.blogapplication.service.impl;

import com.ttu.blogapplication.exception.BlogApplicationException;
import com.ttu.blogapplication.model.Role;
import com.ttu.blogapplication.model.User;
import com.ttu.blogapplication.payload.RegisterDto;
import com.ttu.blogapplication.repository.RoleRepository;
import com.ttu.blogapplication.repository.UserRepository;
import com.ttu.blogapplication.service.RegisterService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class RegisterDtoImplementation implements RegisterService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public RegisterDtoImplementation(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String registerUser(RegisterDto registerDto) {
        if(userRepository.existsByUserName(registerDto.getUserName()))
            throw new BlogApplicationException("Username or email already exists!.",HttpStatus.BAD_REQUEST);
        User user = modelMapper.map(registerDto, User.class);
        List<Role> roleList = new ArrayList<>();
        Role role = roleRepository.findByRole("USER");
        roleList.add(role);
        user.setRoles(roleList);
        userRepository.save(user);
        return "Succesfully Registered";
    }
}
