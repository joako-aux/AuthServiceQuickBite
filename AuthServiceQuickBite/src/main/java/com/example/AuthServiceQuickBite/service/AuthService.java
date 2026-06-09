package com.example.AuthServiceQuickBite.service;

import com.example.AuthServiceQuickBite.dto.RegisterRequest;
import com.example.AuthServiceQuickBite.dto.RegisterResponse;
import com.example.AuthServiceQuickBite.entity.Role;
import com.example.AuthServiceQuickBite.entity.UserAuth;
import com.example.AuthServiceQuickBite.repository.UserAuthRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserAuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserAuthRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        UserAuth user = UserAuth.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .rol(Role.CLIENTE)
                .estado(true)
                .fechaCreacion(LocalDateTime.now())
                .ultimoAcceso(null)
                .build();

        repository.save(user);

        return new RegisterResponse(
                "Usuario registrado correctamente"
        );
    }
}