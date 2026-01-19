package com.example.auth_api.service;

import com.example.auth_api.model.ProcessingLog;
import com.example.auth_api.model.User;
import com.example.auth_api.repository.LogRepository;
import com.example.auth_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    private final RestClient restClient = RestClient.create();

    @Value("${internal.token}")
    private String internalToken;

    public String processRequest(String inputText, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Користувача не знайдено"));

        String outputText = restClient.post()
                .uri("http://localhost:8081/api/transform")
                .header("X-Internal-Token", internalToken)
                .body(inputText)
                .retrieve()
                .body(String.class);

        ProcessingLog log = ProcessingLog.builder()
                .userId(user.getId())
                .inputText(inputText)
                .outputText(outputText)
                .build();

        logRepository.save(log);

        return outputText;
    }

}
