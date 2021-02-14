package com.enigma.listsong.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameExistException extends ApplicationException {

    public UsernameExistException() {
        super(HttpStatus.NOT_FOUND, "error." + HttpStatus.NOT_FOUND.value() + ".username");
    }
}
