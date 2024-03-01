package com.example.vocaapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocaapi.dto.FolderRequestDTO;
import com.example.vocaapi.dto.FolderResponseDTO;
import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.service.FolderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
@Log4j2
public class FolderController {
    private final FolderService folderService;

    @GetMapping("/all")
    public List<FolderResponseDTO> getAll(Principal principal) {
        String loginId = principal.getName();
        log.info(loginId);
        List<FolderResponseDTO> result = folderService.getFolderBySecurity(principal);
        log.info(result);
        return result;
    }

    @GetMapping("/{fid}")
    public FolderResponseDTO get(Principal principal,
            @PathVariable(name = "fid") Long fid) {
        return folderService.getFolderOne(fid, principal);
    }

    @PutMapping("/{fid}")
    public Map<String, String> modify(
            Principal principal,
            @PathVariable(name = "fid") Long fid,
            @RequestBody FolderRequestDTO folderRequestDTO) {
        folderService.modifyFoldername(fid, folderRequestDTO.getFoldername(),
                principal);
        return Map.of("RESULT", "SUCCESS");
    }

    @PostMapping("/")
    public Map<String, Long> register(Principal principal,
            @RequestBody FolderRequestDTO folderRequestDTO) {
        Long fid = folderService.createFolder(folderRequestDTO.getFoldername(), principal);
        if (fid == null) {
            return Map.of("RESULT", null);
        } else
            return Map.of("FID", fid);
    }

    @DeleteMapping("/{fid}")
    public Map<String, String> delete(
            Principal principal,
            @PathVariable(name = "fid") Long fid) {
        folderService.deleteFolder(fid, principal);
        return Map.of("RESULT", "SUCCESS");
    }

}
