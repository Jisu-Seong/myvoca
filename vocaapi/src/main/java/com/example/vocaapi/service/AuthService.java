package com.example.vocaapi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocaapi.dto.MemberDTO;
import com.example.vocaapi.entity.JoinRequestDTO;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public boolean join(JoinRequestDTO joinRequestDTO) {
        Optional<Member> result = memberRepository.findByEmail(joinRequestDTO.getEmail());
        if (result == null) {
            Member.toMember(joinRequestDTO, passwordEncoder);
            return true;
        } else {
            return false;
        }
    }

}
