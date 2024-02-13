package com.example.vocaapi.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocaapi.entity.JoinRequestDTO;
import com.example.vocaapi.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/member/join")
    public Map<String, String> join(@ModelAttribute @Valid JoinRequestDTO joinRequestDTO) {
        String email = joinRequestDTO.getEmail();
        String password = joinRequestDTO.getPassword();
        log.info("==========================join request info============================");
        log.info(email);
        log.info(password);

        // 1. 유효성 검증
        if (email.length() == 0 || password.length() == 0) {
            return Map.of("MESSAGE", "ERROR");
        }

        // 2. service에 투하
        boolean result = authService.join(joinRequestDTO);

        // 3. 결과 보냄
        if (result) {
            return Map.of("MESSAGE", "SUCCESS");
        } else {
            return Map.of("MESSAGE", "EXIST");
        }

    }
}
