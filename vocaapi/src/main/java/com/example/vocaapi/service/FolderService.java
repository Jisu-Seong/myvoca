package com.example.vocaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.vocaapi.config.SecurityUtil;
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

    public Long createFolder(String foldername) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        Folder folder = Folder.builder().foldername(foldername).member(member).build();
        return folderRepository.save(folder).getFid();
    }

    public List<FolderResponseDTO> getFolderBySecurity() {
        List<Folder> folders = folderRepository.findByMember(SecurityUtil.getCurrentMemberId());
        if (folders != null && folders.size() != 0) {
            return folders.stream().map(folder -> FolderResponseDTO.of(folder)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public FolderResponseDTO getFolderOne(Long fid) {
        Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new RuntimeException("폴더 정보가 없습니다."));
        return FolderResponseDTO.of(folder);
    }

    public FolderResponseDTO modifyFoldername(Long fid, String foldername) {
        Folder folder = folderRepository.findByFid(fid).orElseThrow(() -> new RuntimeException("폴더 정보가 없습니다."));
        folder.setFoldername(foldername);
        return FolderResponseDTO.of(folderRepository.save(folder));
    }

    public void deleteFolder(Long fid) {
        folderRepository.deleteById(fid);
    }
}
