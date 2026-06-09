package com.example.AuthServiceQuickBite.controller;

import com.example.AuthServiceQuickBite.dto.RegisterRequest;
import com.example.AuthServiceQuickBite.dto.RegisterResponse;
import com.example.AuthServiceQuickBite.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity.ok(response);
    }
}
