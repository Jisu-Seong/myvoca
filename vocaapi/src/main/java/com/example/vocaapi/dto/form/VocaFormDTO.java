package com.example.vocaapi.dto.form;

import java.util.Set;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VocaFormDTO {
    private String vocaname;
    private boolean isMarked;
    private String meaning;
    private String sentence;
    private Set<String> classes;
}
