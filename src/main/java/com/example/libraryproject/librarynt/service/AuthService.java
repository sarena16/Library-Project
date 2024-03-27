package com.example.libraryproject.librarynt.service;

import com.example.libraryproject.librarynt.controller.dto.CreateUserDto;
import com.example.libraryproject.librarynt.controller.dto.CreateUserLoginDto;
import com.example.libraryproject.librarynt.controller.dto.CreateUserResponseDto;
import com.example.libraryproject.librarynt.controller.dto.LoginResponseDto;
import com.example.libraryproject.librarynt.infrastructure.entity.AuthEntity;
import com.example.libraryproject.librarynt.infrastructure.entity.UserEntity;
import com.example.libraryproject.librarynt.infrastructure.repository.AuthRepository;
import com.example.libraryproject.librarynt.infrastructure.repository.UserRepository;
import com.example.libraryproject.librarynt.service.error.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;



    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;

    }
    @Transactional
    public CreateUserResponseDto register(CreateUserDto dto){
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());

        if (existingAuth.isPresent()){
            throw UserAlreadyExistsException.create(dto.getUsername());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);

        return new CreateUserResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }

    public LoginResponseDto login(CreateUserLoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);


        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw new RuntimeException();
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);
    }
}
