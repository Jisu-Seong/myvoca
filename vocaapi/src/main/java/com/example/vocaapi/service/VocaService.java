package com.example.vocaapi.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.dto.SortRequestDTO;
import com.example.vocaapi.dto.VocaRequestDTO;
import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.entity.Tag;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.MemberRepository;
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
    private final TagRepository tRepository;

    // 보카 추가
    public Vocabulary addVoca(Long fid, VocaRequestDTO vocaRequestDTO, List<Tag> tags,
            Principal principal) {
        Optional<Member> memberResult = memberRepository.findByEmail(principal.getName());
        Member member = memberResult.orElseThrow();

        Optional<Folder> result = folderRepository.findByFid(fid);
        Folder folder = result.orElseThrow();
        if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
            Vocabulary voca = Vocabulary.builder()
                    .folder(folder)
                    .vocaname(vocaRequestDTO.getVocaname())
                    .meaning(vocaRequestDTO.getMeaning())
                    .sentence(vocaRequestDTO.getSentence())
                    .isMarked(vocaRequestDTO.isMarked())
                    .tags(tags)
                    .build();
            return vRepository.save(voca);
        }
        return null;
    }

    // 한 폴더당 보카 리스트
    public PageResponseDTO<VocaResponseDTO> getVocaList(Principal principal,
            Long fid, String sortname, int page, int size) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        Folder folder = null;
        if (fid == null) {
            Optional<Folder> result2 = folderRepository.getOneFolderByMember(member.getEmail()).ofNullable(null);
            folder = result2.orElseGet(null);
        } else {
            folder = folderRepository.getReferenceById(fid);
        }
        if (member == folder.getMember()) {
            Sort sort = Sort.by(isASC(sortname) ? Sort.Direction.ASC : Sort.Direction.DESC, sortname);
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Vocabulary> pages = vRepository.findByFid(fid, pageable);
            List<Vocabulary> list = pages.getContent();
            long totalElements = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            int currentPage = pages.getNumber();

            List<VocaResponseDTO> resList = list.stream().map(x -> VocaResponseDTO.of(x))
                    .collect(Collectors.toList());

            return new PageResponseDTO<VocaResponseDTO>(resList, totalElements,
                    totalPages,
                    currentPage);
        } else {
            throw new RuntimeException("유저 정보가 일치하지 않습니다.");
        }
    }

    private boolean isASC(String sortname) {
        switch (sortname) {
            case "vocaname":
                return true;
            case "created_At":
                return true;
            case "updated_At":
                return false;
            default:
                return true;
        }
    }

    // 한 태그에 해당하는 모든 보카 조회

    // 보카 상세 o
    public VocaResponseDTO getOneVoca(Long vid, Principal principal) {
        Optional<Member> memberResult = memberRepository.findByEmail(principal.getName());
        Member member = memberResult.orElseThrow();

        Vocabulary vocabulary = vRepository.findByVid(vid);
        if (vocabulary != null) {
            Folder folder = vocabulary.getFolder();
            if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
                List<String> list = tRepository.getTagList(vid);
                VocaResponseDTO vocaResponseDTO = VocaResponseDTO.of(vocabulary);
                vocaResponseDTO.setTags(list);
                return vocaResponseDTO;
            }
        }
        return null;
    }

    // 보카 삭제 o
    public void deleteVoca(Long vid, Principal principal) {
        Optional<Member> memberResult = memberRepository.findByEmail(principal.getName());
        Member member = memberResult.orElseThrow();

        Vocabulary vocabulary = vRepository.findByVid(vid);
        if (vocabulary != null) {
            Folder folder = vocabulary.getFolder();
            if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
                vRepository.deleteById(vid);
            }
        }
    }

    // 보카 편집
    public Vocabulary modifyVoca(Long vid, VocaRequestDTO vocaRequestDTO, Principal principal) {
        Optional<Member> memberResult = memberRepository.findByEmail(principal.getName());
        Member member = memberResult.orElseThrow();

        Vocabulary v = vRepository.findByVid(vid);
        if (v != null) {
            Folder folder = v.getFolder();
            if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
                v.changeVocaname(vocaRequestDTO.getVocaname());
                v.changeMeanings(vocaRequestDTO.getMeaning());
                v.changeSentence(vocaRequestDTO.getSentence());
                v.changeMark(vocaRequestDTO.isMarked());
                v.changeUpdateAt();
            }
        }
        return v;

    }

    // 한 단어에 해당되는 모든 태그 조회

    // 태그가 존재하는지 확인
    public boolean isExistTag(String tag) {
        Tag result = tRepository.findByTagname(tag);
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
    public List<Tag> addTags(List<String> tags) {
        List<Tag> list = new ArrayList<>();
        for (String s : tags) {
            Tag result = tRepository.findByTagname(s);
            if (result == null) {
                list.add(tRepository.save(new Tag(s)));
            } else {
                list.add(result);

            }
        }
        return list;
    }

    // 태그 삭제
    public void deleteTags(List<String> tags) {
        for (String s : tags) {
            Tag result = tRepository.findByTagname(s);
            if (result != null) {
                tRepository.delete(result);
            }
        }
    }

}
