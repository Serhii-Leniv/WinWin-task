package com.example.auth_api.controller;

import com.example.auth_api.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/process")
    public ResponseEntity<String> process(@RequestBody String text, Authentication authentication) {
        String email = authentication.getName();

        String result = processService.processRequest(text, email);

        return ResponseEntity.ok(result);
    }

}
