package com.example.vocaapi.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.vocaapi.entity.Authority;
import com.example.vocaapi.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDTO {
	private String email;
	private String password;
	private String nickname;

	public Member toMember(PasswordEncoder passwordEncoder) {
		return Member.builder()
				.email(email)
				.password(passwordEncoder.encode(password))
				.nickname(nickname)
				.authority(Authority.ROLE_USER)
				.build();
	}

	public UsernamePasswordAuthenticationToken toAuthentication() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}