package com.example.auth_api.controller;

import com.example.auth_api.dto.AuthDto;
import com.example.auth_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

        private final AuthService authService;

        @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDto.AuthRequest request) {
            authService.register(request);
            return ResponseEntity.ok("User registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<AuthDto.AuthResponse> login(@RequestBody AuthDto.AuthRequest request){
            String token = authService.login(request);
            return ResponseEntity.ok(new AuthDto.AuthResponse(token));
        }
}
