package com.eticaret.secondHand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotActiveException extends Throwable {

    private static final String message  = "User wanted update is not active!";

    public UserNotActiveException() {
        super(this.message);
    }

    public UserNotActiveException(String message) {
        super(message);
    }
}
