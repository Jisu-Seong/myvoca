package com.example.vocaapi.dto;

import java.time.LocalDateTime;

import com.example.vocaapi.entity.Folder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class FolderResponseDTO {
    private Long fid;
    private String foldername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FolderResponseDTO of(Folder folder) {
        return FolderResponseDTO.builder()
                .fid(folder.getFid())
                .foldername(folder.getFoldername())
                .createdAt(folder.getCreatedAt())
                .updatedAt(folder.getUpdatedAt())
                .build();
    }
}
