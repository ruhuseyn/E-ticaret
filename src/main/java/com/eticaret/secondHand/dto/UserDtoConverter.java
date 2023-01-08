package com.eticaret.secondHand.dto;

import com.eticaret.secondHand.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from){
      return new UserDto(from.getMail(), from.getFirstName(), from.getLastName(), from.getMiddleName());
    }
}
