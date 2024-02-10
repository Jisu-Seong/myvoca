package com.example.vocaapi.service;

import com.example.vocaapi.dto.PageRequestDTO;
import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.dto.TodoDTO;

public interface TodoService {
    Long register(TodoDTO TodoDTO);

    TodoDTO get(Long tno);

    void modify(TodoDTO todoDTO);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
