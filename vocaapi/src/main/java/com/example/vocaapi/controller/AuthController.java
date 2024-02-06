package com.example.vocaapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocaapi.dto.MemberRequestDTO;
import com.example.vocaapi.dto.MemberResponseDto;
import com.example.vocaapi.dto.TokenDTO;
import com.example.vocaapi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDTO requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody MemberRequestDTO requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @RequestMapping("/logout")
    public Map<String, String> logout(@RequestBody TokenDTO tokenDTO) {
        authService.logout(tokenDTO.getAccessToken(), tokenDTO.getRefreshToken());
        return Map.of("RESULT", "SUCCESS");
    }

    @PostMapping("/refresh/{rToken}")
    public ResponseEntity<TokenDTO> refresh(@RequestBody TokenDTO tokenDTO) {
        return ResponseEntity.ok(authService.refresh(tokenDTO.getAccessToken(), tokenDTO.getRefreshToken()));
    }
}