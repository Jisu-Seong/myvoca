package com.example.vocaapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PageResponseDTO<E> {
    private List<E> dtoList;
    private long totalElements;
    private int totalPage;
    private int currentPage;

}
