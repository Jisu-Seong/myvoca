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

import com.example.vocaapi.dto.PageRequestDTO;
import com.example.vocaapi.dto.PageResponseDTO;
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

        public VocaResponseDTO createVoca(VocaRequestDTO vocaRequestDTO) {
                Folder folder = folderRepository.getReferenceById(vocaRequestDTO.getFid());
                Vocabulary vocabulary = Vocabulary.builder()
                                .folder(folder)
                                .vocaname(vocaRequestDTO.getVocaname())
                                .isMarked(vocaRequestDTO.isMarked())
                                .build();
                return VocaResponseDTO.of(vocaRepository.save(vocabulary));
        }

        public PageResponseDTO<VocaResponseDTO> getVocaListByFolder(Long fid, PageRequestDTO PageRequestDTO) {
                Pageable pageable = PageRequest.of(
                                PageRequestDTO.getPage() - 1,
                                PageRequestDTO.getSize(),
                                Sort.by("vid").descending());

                Page<Vocabulary> result = vocaRepository.findVocaPageListByFolder(fid, pageable);
                List<VocaResponseDTO> dtoList = result.getContent().stream()
                                .map((voca) -> VocaResponseDTO.of(voca)).collect(Collectors.toList());

                long totalCount = result.getTotalElements();

                PageResponseDTO<VocaResponseDTO> responseDTO = PageResponseDTO.<VocaResponseDTO>withAll()
                                .dtoList(dtoList)
                                .pageRequestDTO(PageRequestDTO)
                                .totalCount(totalCount)
                                .build();

                return responseDTO;
        }

        public VocaResponseDTO get(Long vid) {
                Optional<Vocabulary> result = vocaRepository.findById(vid);
                Vocabulary vocabulary = result.orElseThrow();
                return VocaResponseDTO.of(vocabulary);
        }

        public void modify(VocaRequestDTO vocaRequestDTO) {
                Optional<Vocabulary> result = vocaRepository.findById(vocaRequestDTO.getVid());
                Vocabulary vocabulary = result.orElseThrow();

                vocabulary.changeVocaname(vocaRequestDTO.getVocaname());
                vocabulary.changeMark(vocaRequestDTO.isMarked());

                vocaRepository.save(vocabulary);
        }

        public void remove(Long vid) {
                vocaRepository.deleteById(vid);
        }
}
