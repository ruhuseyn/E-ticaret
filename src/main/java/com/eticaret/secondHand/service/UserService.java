package com.eticaret.secondHand.service;

import com.eticaret.secondHand.dto.CreateUserRequest;
import com.eticaret.secondHand.dto.UpdateUserRequest;
import com.eticaret.secondHand.dto.UserDto;
import com.eticaret.secondHand.dto.UserDtoConverter;
import com.eticaret.secondHand.exception.UserNotFoundException;
import com.eticaret.secondHand.model.User;
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

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user =findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(null,
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getMiddleName());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);
        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName());
        return userDtoConverter.convert(updatedUser);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).
                 orElseThrow(() -> new UserNotFoundException("User couldn't be found following id: " + id));
    }
}

