package com.example.vocaapi.entity;

import java.time.LocalDateTime;
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

    private String orgFileName;

    private String storedFileName;

    private Long fileSize;

    private LocalDateTime regDate;

    private boolean delFlag;
}
