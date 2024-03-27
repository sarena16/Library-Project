package com.example.libraryproject.librarynt.controller;

import com.example.libraryproject.librarynt.controller.dto.*;
import com.example.libraryproject.librarynt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

   private final UserService userService;

    @Autowired
   public UserController(UserService userService) {
       this.userService = userService;
    }
    @GetMapping
    public List<GetUserDto> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetUserDto getOne(@PathVariable long id){
        return userService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> create(@RequestBody CreateUserDto user) {
        var newUser =  userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
