package com.example.data_api.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @Value("${internal.token}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<String> transform(
            @RequestBody String text,
            @RequestHeader("X-Internal-Token") String incomingToken) {

        if (!internalToken.equals(incomingToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
        }

        String transformed = text.toUpperCase() + " [Processed by data Service]";

        return ResponseEntity.ok(transformed);
    }
}