package com.example.vocaapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.vocaapi.entity.Vocabulary;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class VocaResponseDTO {
    private Long vid;
    private Long fid;
    private String vocaname;
    private boolean isMarked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String meaning;
    private String sentence;
    private List<String> tags;

    public static VocaResponseDTO of(Vocabulary vocabulary) {
        return VocaResponseDTO.builder()
                .vid(vocabulary.getVid())
                .fid(vocabulary.getFolder().getFid())
                .vocaname(vocabulary.getVocaname())
                .isMarked(vocabulary.isMarked())
                .createdAt(vocabulary.getCreatedAt())
                .updatedAt(vocabulary.getUpdatedAt())
                .meaning(vocabulary.getMeaning())
                .sentence(vocabulary.getSentence())
                .build();
    }

}
