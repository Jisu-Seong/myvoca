package com.example.vocaapi.entity;

import java.time.LocalDateTime;

import com.example.vocaapi.dto.MemberRequestDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileImgFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @OneToOne
    @JoinColumn(name = "mid")
    private Member member;

    private String fileName;

    private boolean delFlag;

}
