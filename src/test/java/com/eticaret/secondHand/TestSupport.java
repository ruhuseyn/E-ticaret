package com.eticaret.secondHand;

import com.eticaret.secondHand.dto.UserDto;
import com.eticaret.secondHand.model.User;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public List<User> generateUser(){
        return IntStream.range(0,5).mapToObj(i ->
                new User((long)i,
                        i+ "ruhusseyn@gmal.com",
                        i+ "firstname",
                        i+ "lastname",
                        " ",
                        new Random(2).nextBoolean())).collect(Collectors.toList());
    }
    public List<UserDto> generateUserDto(List<User> userList){
        return userList.stream().
                map(from ->new UserDto(from.getMail(), from.getFirstName(), from.getLastName(), from.getMiddleName())).
                collect(Collectors.toList());
    }
}
