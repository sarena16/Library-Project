package com.example.libraryproject.librarynt.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException  {

    public static ResponseStatusException create (String username){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username :%s already exists.", username));

    }
}
