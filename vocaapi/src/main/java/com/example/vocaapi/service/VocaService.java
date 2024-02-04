package com.example.vocaapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vocaapi.config.SecurityUtil;
import com.example.vocaapi.dto.VocaRequestDTO;
import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.entity.Relation;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.entity.Tag;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.MemberRepository;
import com.example.vocaapi.repository.RelationRepository;
import com.example.vocaapi.repository.VocaRepository;
import com.example.vocaapi.repository.TagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class VocaService {
        private final MemberRepository memberRepository;
        private final FolderRepository folderRepository;
        private final VocaRepository vRepository;
        private final RelationRepository rRepository;
        private final TagRepository tRepository;

        // 폴더당 보카 리스트 수정요
        public List<VocaResponseDTO> getVocaList(Long fid) {

                Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
                List<Folder> fList = folderRepository.findByMember(member.getMid());

                Optional<Folder> folder = folderRepository.findByFid(fid);

                if (folder != null && fList != null && fList.size() != 0) {
                        List<Vocabulary> vList = vRepository.findVocaPageListByFid(fid);
                        return vList.stream().map(voca -> VocaResponseDTO.of(voca)).collect(Collectors.toList());

                } else {
                        return null;
                }

        }

        // 보카 상세 o
        public VocaResponseDTO getOneVoca(Long vid) {
                Optional<Vocabulary> result = vRepository.findByVid(vid);
                return VocaResponseDTO.of(result.orElseThrow());
        }

        // 보카 삭제 o
        public void deleteVoca(Long vid) {
                vRepository.deleteById(vid);
        }

        // 보카 편집
        public void modifyVoca(Long vid, VocaRequestDTO vocaRequestDTO) {
                Optional<Vocabulary> result = vRepository.findByVid(vid);
                Vocabulary v = result.orElseThrow();
                v.changeVocaname(vocaRequestDTO.getVocaname());
                v.changeMeanings(vocaRequestDTO.getMeaning());
                v.changeSentence(vocaRequestDTO.getSentence());
                v.changeMark(vocaRequestDTO.isMarked());
                v.changeUpdateAt();

        }

        // 보카 추가
        public VocaResponseDTO addVoca(Long fid, VocaRequestDTO vocaRequestDTO) {
                Folder folder = folderRepository.getReferenceById(fid);
                Vocabulary voca = Vocabulary.builder()
                                .folder(folder)
                                .vocaname(vocaRequestDTO.getVocaname())
                                .meaning(vocaRequestDTO.getMeaning())
                                .sentence(vocaRequestDTO.getSentence())
                                .isMarked(vocaRequestDTO.isMarked())
                                .build();
                return VocaResponseDTO.of(vRepository.save(voca));
        }

        // 한 태그에 해당하는 모든 보카 조회

        // 한 단어에 해당되는 모든 태그 조회
        public List<String> findAllTagsByVoca(Long vid) {
                Vocabulary v = vRepository.getReferenceById(vid);
                List<Relation> list = rRepository.findRelationByVid(v.getVid());
                if (list != null && list.size() != 0) {
                        return list.stream().map(x -> x.getTag().getTagname()).collect(Collectors.toList());
                } else {
                        return null;
                }

        }

        // 관계 추가
        public void addRelation(Long vid, List<String> tags) {
                Vocabulary v = vRepository.getReferenceById(vid);
                for (String tag : tags) {
                        Optional<Tag> result = tRepository.findByTagname(tag);
                        if (result != null) {
                                Tag t = result.orElseThrow();
                                rRepository.save(new Relation(v, t));
                        }
                }

        }

        // 관계 삭제
        public void deleteRelation(Long vid) {
                rRepository.deleteRelationByVid(vid);
        }

        // 태그가 존재하는지 확인
        public boolean isExistTag(String tag) {
                Optional<Tag> result = tRepository.findByTagname(tag);
                if (result == null) {
                        return false;
                }
                return true;
        }

        // 태그 비교 후 추가할것 리턴
        public List<String> compareTagList(List<String> before, List<String> after) {
                after.removeAll(before);
                return after;

        }

        // 태그 추가
        public void addTags(List<String> tags) {
                for (String s : tags) {
                        Optional<Tag> result = tRepository.findByTagname(s);
                        if (result == null) {
                                tRepository.save(new Tag(s));
                        }
                }
        }

        // 태그 삭제
        public void deleteTags(List<String> tags) {
                for (String s : tags) {
                        Optional<Tag> result = tRepository.findByTagname(s);
                        Tag t = result.orElseThrow();
                        tRepository.delete(t);
                }
        }

}
