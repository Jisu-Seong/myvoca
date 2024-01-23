package com.example.vocaapi.dto;

import java.time.LocalDateTime;

import com.example.vocaapi.entity.Vocabulary;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class VocaResponseDTO {
    private Long vid;
    private String vocaname;
    private boolean isMarked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VocaResponseDTO of(Vocabulary vocabulary) {
        return VocaResponseDTO.builder()
                .vid(vocabulary.getVid())
                .vocaname(vocabulary.getVocaname())
                .isMarked(vocabulary.isMarked())
                .createdAt(vocabulary.getCreatedAt())
                .updatedAt(vocabulary.getUpdatedAt())
                .build();
    }

}
