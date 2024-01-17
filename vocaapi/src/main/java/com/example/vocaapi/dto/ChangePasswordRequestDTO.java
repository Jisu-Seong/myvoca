package com.example.vocaapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordRequestDTO {
    private String email;
    private String exPassword;
    private String newPassword;
}
