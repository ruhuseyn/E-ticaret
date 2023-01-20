package com.eticaret.secondHand.service;

import com.eticaret.secondHand.dto.CreateUserRequest;
import com.eticaret.secondHand.dto.UpdateUserRequest;
import com.eticaret.secondHand.dto.UserDto;
import com.eticaret.secondHand.dto.UserDtoConverter;
import com.eticaret.secondHand.exception.UserNotActiveException;
import com.eticaret.secondHand.exception.UserNotFoundException;
import com.eticaret.secondHand.model.User;
import com.eticaret.secondHand.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserById(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(null,
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getMiddleName(),
                true);
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) throws UserNotActiveException {
        User user = findUserByMail(mail);
        if (!user.getActive()) {
            logger.warning(String.format("User wanted update is not active, user mail: %s",mail));
            throw new UserNotActiveException();
        }
        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName());
        return userDtoConverter.convert(updatedUser);
    }
    public void deactivateUser(Long id) {
        changeActivate(id,false);
    }

    public void activeUser(Long id) {
        changeActivate(id,true);
    }


    public void deleteUser(Long id) {
        if (doesUserExist(id)) {
            userRepository.deleteById(id);
        }else{
            new UserNotFoundException("User couldn't be found following id: " + id);
        }
    }

    private void changeActivate(Long id, Boolean isActive){
        User user = findUserById(id);

        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                isActive);
        userRepository.save(updatedUser);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User couldn't be found following id: " + id));
    }

    private User findUserByMail(String mail) {
        return userRepository.findByMail(mail).
                orElseThrow(() -> new UserNotFoundException("User couldn't be found following mail: " + mail));
    }

    private boolean doesUserExist(Long id){
        return userRepository.existsById(id);
    }
}

