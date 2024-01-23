package com.example.vocaapi.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class VocaRequestDTO {
    private Long fid;
    private Long vid;
    private String vocaname;
    private boolean isMarked;
}
