package com.example.vocaapi.service;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocaapi.dto.MemberRequestDTO;
import com.example.vocaapi.dto.MemberResponseDto;
import com.example.vocaapi.dto.TokenDTO;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.jwt.TokenProvider;
import com.example.vocaapi.redis.RedisUtil;
import com.example.vocaapi.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    public MemberResponseDto signup(MemberRequestDTO requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDTO login(MemberRequestDTO requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

    public void logout(String accessToken, String refreshToken) {
        redisUtil.setBlackList(accessToken, "accessToken", 1800);
        redisUtil.setBlackList(refreshToken, "refreshToken", 60400);
    }

    public TokenDTO refresh(String aToken, String rToken) {
        if (!tokenProvider.validateToken(rToken)) {
            throw new RuntimeException("유효하지 않은 refreshToken입니다.");
        }
        Authentication authentication = tokenProvider.getAuthentication(aToken);
        return tokenProvider.generateTokenDto(authentication);

    }

}
