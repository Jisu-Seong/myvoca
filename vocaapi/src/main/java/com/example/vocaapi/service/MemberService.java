package com.example.vocaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocaapi.config.SecurityUtil;
import com.example.vocaapi.dto.MemberResponseDto;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	@Autowired
	private final MemberRepository memberRepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;

	public MemberResponseDto getMyInfoBySecurity() {
		return memberRepository.findById(SecurityUtil.getCurrentMemberId()).map(MemberResponseDto::of)
				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
	}

	public MemberResponseDto changeMemberNickname(String email, String nickname) {
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
		member.setNickname(nickname);
		return MemberResponseDto.of(memberRepository.save(member));
	}

	public MemberResponseDto changeMemberPassword(String exPassword, String newPassword) {
		Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
		if (!passwordEncoder.matches(exPassword, member.getPassword())) {
			throw new RuntimeException("비밀번호가 맞지 않습니다.");
		}
		member.setPassword(passwordEncoder.encode(newPassword));
		return MemberResponseDto.of(memberRepository.save(member));
	}

}