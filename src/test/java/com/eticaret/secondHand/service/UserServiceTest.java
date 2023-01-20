package com.eticaret.secondHand.service;

import com.eticaret.secondHand.dto.UserDtoConverter;
import com.eticaret.secondHand.repository.UserRepository;
import com.eticaret.secondHand.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest  {

    private UserDtoConverter userDtoConverter;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeAll
    public void setUp(){
        userDtoConverter = mock(UserDtoConverter.class);
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository,userDtoConverter);
    }
}