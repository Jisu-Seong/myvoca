package com.example.vocaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocaapi.config.SecurityUtil;
import com.example.vocaapi.dto.MemberResponseDTO;
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

	public MemberResponseDTO getMyInfoBySecurity() {
		return memberRepository.findById(SecurityUtil.getCurrentMemberId()).map(MemberResponseDTO::of)
				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
	}

	public MemberResponseDTO changeMemberNickname(String email, String nickname) {
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
		member.setNickname(nickname);
		return MemberResponseDTO.of(memberRepository.save(member));
	}

	public MemberResponseDTO changeMemberPassword(String exPassword, String newPassword) {
		Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
		if (!passwordEncoder.matches(exPassword, member.getPassword())) {
			throw new RuntimeException("비밀번호가 맞지 않습니다.");
		}
		member.setPassword(passwordEncoder.encode(newPassword));
		return MemberResponseDTO.of(memberRepository.save(member));
	}

}