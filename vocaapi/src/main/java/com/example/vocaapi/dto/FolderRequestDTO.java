package com.example.vocaapi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class FolderRequestDTO {
    private Long mid;
    private Long fid;
    private String foldername;
}
