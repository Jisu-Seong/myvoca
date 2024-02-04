package com.example.vocaapi.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VocaFormDTO {
    @JsonProperty("vocaRequestDTO")
    private VocaRequestDTO vocaRequestDTO;

    @JsonProperty("tags")
    private List<String> tags;
}
