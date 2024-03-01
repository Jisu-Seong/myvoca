package com.example.vocaapi.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.vocaapi.dto.FolderResponseDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Member;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Log4j2
@Service
@RequiredArgsConstructor
public class FolderService {
    private final MemberRepository memberRepository;
    private final FolderRepository folderRepository;

    public Long createFolder(String foldername, Principal principal) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        int count = folderRepository.isExistFoldername(foldername);
        if (count == 0) {
            Folder folder = Folder.builder().foldername(foldername).member(member).build();
            return folderRepository.save(folder).getFid();
        } else {
            return null;
        }
    }

    public List<FolderResponseDTO> getFolderBySecurity(Principal principal) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        List<Folder> folders = folderRepository.findByMember(member.getEmail());

        return folders.stream().map(folder -> FolderResponseDTO.of(folder)).collect(Collectors.toList());

    }

    public FolderResponseDTO getFolderOne(Long fid, Principal principal) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new RuntimeException("폴더 정보가 없습니다."));
        if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
            return FolderResponseDTO.of(folder);
        }
        return null;
    }

    public FolderResponseDTO modifyFoldername(Long fid, String foldername,
            Principal principal) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new RuntimeException("폴더 정보가 없습니다."));
        if (folder != null && member.getEmail().equals(folder.getMember().getEmail())) {
            folder.setFoldername(foldername);
            return FolderResponseDTO.of(folderRepository.save(folder));
        }
        return null;
    }

    public void deleteFolder(Long fid, Principal principal) {
        Optional<Member> result = memberRepository.findByEmail(principal.getName());
        Member member = result.orElseThrow();
        Optional<Folder> result2 = folderRepository.findByFid(fid);
        if (result2 != null) {
            Folder folder = result2.orElseThrow();
            if (member.getEmail().equals(folder.getMember().getEmail())) {
                folderRepository.deleteById(fid);
            } else {
                throw new RuntimeException("삭제할 권한이 없습니다.");
            }
        } else {
            throw new RuntimeException("존재하지 않는 폴더입니다.");
        }
    }
}
