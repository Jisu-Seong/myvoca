package com.example.vocaapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponseDTO<E> {
    private List<E> dtoList;
    private long totalElements;
    private int totalCount;
    private int currentPage;

    public PageResponseDTO(List<E> dtoList, long totalElements, int totalCount, int currentPage) {
        this.dtoList = dtoList;
        this.totalCount = (int) totalCount;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
    }
}
