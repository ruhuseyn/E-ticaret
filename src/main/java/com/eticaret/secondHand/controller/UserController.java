package com.eticaret.secondHand.controller;

import com.eticaret.secondHand.dto.CreateUserRequest;
import com.eticaret.secondHand.dto.UpdateUserRequest;
import com.eticaret.secondHand.dto.UserDto;
import com.eticaret.secondHand.exception.UserNotActiveException;
import com.eticaret.secondHand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserByiD(@PathVariable("mail") String mail) {
        return ResponseEntity.ok(userService.getUserById(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/{mail}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable String mail,
            @RequestBody UpdateUserRequest updateUserRequest) throws UserNotActiveException {
        return ResponseEntity.ok(userService.updateUser(mail,updateUserRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactiveUser(@PathVariable("id") Long id){
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> activeUser(@PathVariable("id") Long id){
        userService.activeUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
