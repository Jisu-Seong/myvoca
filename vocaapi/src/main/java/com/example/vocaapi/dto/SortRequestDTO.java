package com.example.vocaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SortRequestDTO {
    private boolean marked;
    private String sortname;
    private boolean asc;

}
