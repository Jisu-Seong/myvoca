package com.example.vocaapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.dto.form.VocaFormDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.entity.Wordclass;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.VocaAndClassRepository;
import com.example.vocaapi.repository.VocaRepository;
import com.example.vocaapi.repository.WordclassRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class VocaService {
        private final VocaRepository vRepository;
        private final VocaAndClassRepository vcRepository;
        private final WordclassRepository wRepository;

        // 폴더당 보카 리스트 o
        public List<VocaResponseDTO> getVocaList(Long fid) {
                List<Vocabulary> list = vRepository.findVocaPageListByFid(fid);
                return list.stream().map(voca -> VocaResponseDTO.of(voca)).collect(Collectors.toList());
        }

        // 보카 상세 o
        public VocaResponseDTO getOneVoca(Long vid) {
                return VocaResponseDTO.of(vRepository.getReferenceById(vid));
        }

        // 보카 삭제 o,
        // 관계도 삭제
        public void deleteVoca(Long vid) {
                vRepository.deleteById(vid);

        }

        // 보카 편집
        // 클래스도 경우에 따라 추가/삭제
        public void modifyVoca(Long vid, VocaFormDTO vocaFormDTO) {
                Vocabulary vocabulary = vRepository.getReferenceById(vid);
                vocabulary.changeVocaname(vocaFormDTO.getVocaname());
                vocabulary.changeMark(vocaFormDTO.isMarked());
                vocabulary.changeMeanings(vocaFormDTO.getMeaning());
                vocabulary.changeSentence(vocaFormDTO.getSentence());
                vocabulary.changeUpdateAt();
                vRepository.save(vocabulary);
        }

        // 보카 추가
        // 관계 추가, 클래스 확인 후 추가
        public Long addVoca(Long fid, VocaFormDTO vocaFormDTO) {
                Vocabulary vocabulary = Vocabulary.builder()
                                .vocaname(vocaFormDTO.getVocaname())
                                .isMarked(vocaFormDTO.isMarked())
                                .meaning(vocaFormDTO.getMeaning())
                                .sentence(vocaFormDTO.getSentence())
                                .build();
                return vRepository.save(vocabulary).getVid();
        }

        // 한 클래스에 해당하는 모든 보카 조회
        // 일단 클래스 이름에 대하여 클래스가 존재하는지 확인후
        // 그 클래스의 wid를 추출
        // 보카와의 관계를 wid로 알아내어 vid 리스트 추출
        // 각 vid당 보카들을 리스트로 가져옴

        // 한 단어에 해당되는 모든 클래스 조회
        // vid에 해당하는 관계를 확인하고 wid 리스트 추출
        // 각 wid당 클래스를 리스트로 가져옴

        // 관계 추가

        // 관계 삭제

        // 클래스 추가

        // 클래스 삭제

}
