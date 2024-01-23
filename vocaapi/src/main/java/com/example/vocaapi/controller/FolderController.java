package com.example.vocaapi.controller;

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
import com.example.vocaapi.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
public class FolderController {
    private final FolderService folderService;

    @GetMapping("/all")
    public List<FolderResponseDTO> getAll() {
        return folderService.getFolderBySecurity();
    }

    @GetMapping("/{fid}")
    public FolderResponseDTO get(@PathVariable(name = "fid") Long fid) {
        return folderService.getFolderOne(fid);
    }

    @PutMapping("/{fid}")
    public Map<String, String> modify(
            @PathVariable(name = "fid") Long fid,
            @RequestBody FolderRequestDTO folderRequestDTO) {
        folderService.modifyFoldername(fid, folderRequestDTO.getFoldername());
        return Map.of("RESULT", "SUCCESS");
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody FolderRequestDTO folderRequestDTO) {
        Long fid = folderService.createFolder(folderRequestDTO.getFoldername());
        return Map.of("FID", fid);
    }

    @DeleteMapping("/{fid}")
    public Map<String, String> delete(@PathVariable(name = "fid") Long fid) {
        folderService.deleteFolder(fid);
        return Map.of("RESULT", "SUCCESS");
    }

}
