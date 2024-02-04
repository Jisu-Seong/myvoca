package com.example.vocaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VocaRequestDTO {
    @JsonProperty("vocaname")
    private String vocaname;

    @JsonProperty("marked")
    private boolean marked;

    @JsonProperty("meaning")
    private String meaning;

    @JsonProperty("sentence")
    private String sentence;
}
