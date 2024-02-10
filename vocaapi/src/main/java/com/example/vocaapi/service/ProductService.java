package com.example.vocaapi.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.vocaapi.dto.PageRequestDTO;
import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.dto.ProductDTO;

@Transactional
public interface ProductService {
    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);

    ProductDTO get(Long pno);

    void modify(ProductDTO productDTO);

    void remove(Long pno);
}
