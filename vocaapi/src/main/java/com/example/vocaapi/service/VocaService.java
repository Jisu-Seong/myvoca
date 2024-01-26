package com.example.vocaapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vocaapi.dto.VocaRequestDTO;
import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.VocaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class VocaService {
        private final FolderRepository folderRepository;
        private final VocaRepository vocaRepository;

        // 보카 리스트

        // 보카 상세

        // 보카 편집

        // 보카 삭제

        // 보카 추가
}
