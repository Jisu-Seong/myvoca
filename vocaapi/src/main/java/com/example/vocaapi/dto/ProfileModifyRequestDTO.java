package com.example.vocaapi.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileModifyRequestDTO {
    private String nickname;
    private MultipartFile MultipartFile;
}
