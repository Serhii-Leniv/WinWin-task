package com.example.auth_api.service;

import com.example.auth_api.dto.AuthDto;
import com.example.auth_api.model.User;
import com.example.auth_api.repository.UserRepository;
import com.example.auth_api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(AuthDto.AuthRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){ throw new RuntimeException("User with this email exist");}
        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword_hash()))
                .build();
        userRepository.save(user);
    }

    public String login(AuthDto.AuthRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid cred"));

        if(!passwordEncoder.matches(request.getPassword_hash(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid cred");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

}
