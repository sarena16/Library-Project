package com.example.libraryproject.librarynt.service;

import com.example.libraryproject.librarynt.controller.dto.*;
import com.example.libraryproject.librarynt.infrastructure.entity.UserEntity;
import com.example.libraryproject.librarynt.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetUserDto> getAll() {
        var users = userRepository.findAll();

        return users.stream().map((user) -> new GetUserDto(
                user.getId(),
                user.getName()
                )).toList();
    }

    public GetUserDto getOne(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return new GetUserDto(
                user.getId(),
                user.getName()
        );
    }

    public CreateUserResponseDto create(CreateUserDto user) {
        var userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());


        var newUser = userRepository.save(userEntity);

        return new CreateUserResponseDto(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getName()
                );
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }


}