package com.eticaret.secondHand.service;

import com.eticaret.secondHand.dto.UserDto;
import com.eticaret.secondHand.dto.UserDtoConverter;
import com.eticaret.secondHand.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }
}

