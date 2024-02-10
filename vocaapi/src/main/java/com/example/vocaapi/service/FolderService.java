// package com.example.vocaapi.service;

// import java.security.Principal;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.example.vocaapi.config.SecurityUtil;
// import com.example.vocaapi.dto.FolderResponseDTO;
// import com.example.vocaapi.entity.Folder;
// import com.example.vocaapi.entity.Member;
// import com.example.vocaapi.repository.FolderRepository;
// import com.example.vocaapi.repository.MemberRepository;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Transactional
// @Log4j2
// @Service
// @RequiredArgsConstructor
// public class FolderService {
// private final MemberRepository memberRepository;
// private final FolderRepository folderRepository;

// // mid
// public Long getMidByPrincipal(Principal principal) {
// String loginId = principal.getName();
// return Long.valueOf(Integer.parseInt(loginId));
// }

// public Long createFolder(String foldername) {
// Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
// .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
// Folder folder =
// Folder.builder().foldername(foldername).member(member).build();
// return folderRepository.save(folder).getFid();
// }

// public List<FolderResponseDTO> getFolderBySecurity() {
// List<Folder> folders =
// folderRepository.findByMember(SecurityUtil.getCurrentMemberId());
// if (folders != null && folders.size() != 0) {
// return folders.stream().map(folder ->
// FolderResponseDTO.of(folder)).collect(Collectors.toList());
// } else {
// return null;
// }
// }

// public FolderResponseDTO getFolderOne(Long fid, Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new
// RuntimeException("폴더 정보가 없습니다."));
// if (folder != null && mid == folder.getMember().getMid()) {
// return FolderResponseDTO.of(folder);
// }
// return null;
// }

// public FolderResponseDTO modifyFoldername(Long fid, String foldername,
// Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new
// RuntimeException("폴더 정보가 없습니다."));
// if (folder != null && mid == folder.getMember().getMid()) {
// folder.setFoldername(foldername);
// return FolderResponseDTO.of(folderRepository.save(folder));
// }
// return null;
// }

// public void deleteFolder(Long fid, Principal principal) {
// Long mid = getMidByPrincipal(principal);
// Optional<Folder> result = folderRepository.findByFid(fid);
// if (result != null) {
// Folder folder = result.orElseThrow();
// if (mid == folder.getMember().getMid()) {
// folderRepository.deleteById(fid);
// } else {
// throw new RuntimeException("삭제할 권한이 없습니다.");
// }
// } else {
// throw new RuntimeException("존재하지 않는 폴더입니다.");
// }
// }
// }
