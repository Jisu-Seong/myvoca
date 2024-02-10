// package com.example.vocaapi.service;

// import java.security.Principal;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.Iterator;
// import java.util.LinkedHashSet;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.vocaapi.config.SecurityUtil;
// import com.example.vocaapi.dto.PageResponseDTO;
// import com.example.vocaapi.dto.SortRequestDTO;
// import com.example.vocaapi.dto.VocaRequestDTO;
// import com.example.vocaapi.dto.VocaResponseDTO;
// import com.example.vocaapi.entity.Folder;
// import com.example.vocaapi.entity.Member;
// import com.example.vocaapi.entity.Relation;
// import com.example.vocaapi.entity.Vocabulary;
// import com.example.vocaapi.entity.Tag;
// import com.example.vocaapi.repository.FolderRepository;
// import com.example.vocaapi.repository.MemberRepository;
// import com.example.vocaapi.repository.RelationRepository;
// import com.example.vocaapi.repository.VocaRepository;
// import com.example.vocaapi.repository.TagRepository;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Service
// @Transactional
// @Log4j2
// @RequiredArgsConstructor
// public class VocaService {
// private final MemberRepository memberRepository;
// private final FolderRepository folderRepository;
// private final VocaRepository vRepository;
// private final RelationRepository rRepository;
// private final TagRepository tRepository;

// // mid
// public Long getMidByPrincipal(Principal principal) {
// String loginId = principal.getName();
// return Long.valueOf(Integer.parseInt(loginId));
// }

// // 폴더당 보카 리스트 수정요
// public PageResponseDTO<VocaResponseDTO> getVocaList(Long fid, Principal
// principal,
// SortRequestDTO sortRequestDTO, int page, int size) {
// Long mid = getMidByPrincipal(principal);
// Folder folder = folderRepository.getReferenceById(fid);

// if (folder != null && mid == folder.getMember().getMid()) {
// SortRequestDTO convertSort = convertNullSort(sortRequestDTO);
// Sort sort = Sort.by(convertSort.isAsc() ? Sort.Direction.ASC :
// Sort.Direction.DESC,
// convertSort.getSortname());
// Pageable pageable = PageRequest.of(page, size, sort);
// Page<Vocabulary> pages = vRepository.findByMarked(convertSort.isMarked(),
// pageable);

// List<Vocabulary> list = pages.getContent();
// long totalElements = pages.getTotalElements();
// int totalPages = pages.getTotalPages();
// int currentPage = pages.getNumber();

// List<VocaResponseDTO> resList = list.stream().map(x -> VocaResponseDTO.of(x))
// .collect(Collectors.toList());

// return new PageResponseDTO<>(resList, totalElements, totalPages,
// currentPage);

// }
// return null;
// }

// private SortRequestDTO convertNullSort(SortRequestDTO sortRequestDTO) {
// boolean marked = false;
// String sortname = "updatedAt";
// boolean asc = false;
// if (sortRequestDTO != null) {
// if (sortRequestDTO.getSortname() != null) {
// sortname = sortRequestDTO.getSortname();
// }
// marked = sortRequestDTO.isMarked();
// asc = sortRequestDTO.isAsc();
// }
// return new SortRequestDTO(marked, sortname, asc);

// }

// // 보카 상세 o
// public VocaResponseDTO getOneVoca(Long vid, Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Optional<Vocabulary> result = vRepository.findByVid(vid);
// Vocabulary vocabulary = result.orElseThrow();
// Folder folder = vocabulary.getFolder();
// if (folder != null && mid == folder.getMember().getMid()) {
// return VocaResponseDTO.of(vocabulary);
// }
// return null;
// }

// // 보카 삭제 o
// public void deleteVoca(Long vid, Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Optional<Vocabulary> result = vRepository.findByVid(vid);
// Vocabulary vocabulary = result.orElseThrow();
// Folder folder = vocabulary.getFolder();
// if (folder != null && mid == folder.getMember().getMid()) {
// vRepository.deleteById(vid);
// }
// }

// // 보카 편집
// public void modifyVoca(Long vid, VocaRequestDTO vocaRequestDTO, Principal
// principal) {
// Long mid = getMidByPrincipal(principal);
// Optional<Vocabulary> result = vRepository.findByVid(vid);
// Vocabulary v = result.orElseThrow();
// Folder folder = v.getFolder();
// if (folder != null && mid == folder.getMember().getMid()) {
// v.changeVocaname(vocaRequestDTO.getVocaname());
// v.changeMeanings(vocaRequestDTO.getMeaning());
// v.changeSentence(vocaRequestDTO.getSentence());
// v.changeMark(vocaRequestDTO.isMarked());
// v.changeUpdateAt();
// }

// }

// // 보카 추가
// public VocaResponseDTO addVoca(Long fid, VocaRequestDTO vocaRequestDTO,
// Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Folder folder = folderRepository.getReferenceById(fid);
// if (folder != null && mid == folder.getMember().getMid()) {
// Vocabulary voca = Vocabulary.builder()
// .folder(folder)
// .vocaname(vocaRequestDTO.getVocaname())
// .meaning(vocaRequestDTO.getMeaning())
// .sentence(vocaRequestDTO.getSentence())
// .isMarked(vocaRequestDTO.isMarked())
// .build();
// return VocaResponseDTO.of(vRepository.save(voca));
// }
// return null;
// }

// // 한 태그에 해당하는 모든 보카 조회

// // 한 단어에 해당되는 모든 태그 조회
// public List<String> findAllTagsByVoca(Long vid, Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Optional<Vocabulary> result = vRepository.findByVid(vid);
// Vocabulary v = result.orElseThrow();
// Folder folder = v.getFolder();

// if (folder != null && mid == folder.getMember().getMid()) {
// List<Relation> list = rRepository.findRelationByVid(v.getVid());
// if (list != null && list.size() != 0) {
// return list.stream().map(x ->
// x.getTag().getTagname()).collect(Collectors.toList());
// }
// }
// return null;

// }

// // 관계 추가
// public void addRelation(Long vid, List<String> tags) {
// Vocabulary v = vRepository.getReferenceById(vid);
// for (String tag : tags) {
// Optional<Tag> result = tRepository.findByTagname(tag);
// if (result != null) {
// Tag t = result.orElseThrow();
// rRepository.save(new Relation(v, t));
// }
// }

// }

// // 관계 삭제
// public void deleteRelation(Long vid) {
// rRepository.deleteRelationByVid(vid);
// }

// // 태그가 존재하는지 확인
// public boolean isExistTag(String tag) {
// Optional<Tag> result = tRepository.findByTagname(tag);
// if (result == null) {
// return false;
// }
// return true;
// }

// // 태그 비교 후 추가할것 리턴
// public List<String> compareTagList(List<String> before, List<String> after) {
// after.removeAll(before);
// return after;

// }

// // 태그 추가
// public void addTags(List<String> tags) {
// for (String s : tags) {
// Optional<Tag> result = tRepository.findByTagname(s);
// if (result == null) {
// tRepository.save(new Tag(s));
// }
// }
// }

// // 태그 삭제
// public void deleteTags(List<String> tags) {
// for (String s : tags) {
// Optional<Tag> result = tRepository.findByTagname(s);
// Tag t = result.orElseThrow();
// tRepository.delete(t);
// }
// }

// }
