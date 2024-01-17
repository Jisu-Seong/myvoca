package com.example.vocaapi.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.vocaapi.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberResponseDTO {
    private String email;
    private String nickname;
    private MultipartFile multipartFile;

    public static MemberResponseDTO of(Member member) {
        return MemberResponseDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
