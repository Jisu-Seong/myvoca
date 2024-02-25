package com.example.vocaapi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocaapi.dto.JoinRequestDTO;
import com.example.vocaapi.dto.MemberDTO;
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
        int num = memberRepository.isExistEmail(joinRequestDTO.getEmail());
        System.out.println(num);
        log.info(num);

        if (num == 0) {
            Member member = Member.toMember(joinRequestDTO, passwordEncoder);
            memberRepository.save(member);
            return true;
        } else {
            return false;
        }
    }

}
