package com.example.vocaapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocaapi.dto.PageRequestDTO;
import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.dto.VocaRequestDTO;
import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.service.VocaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voca")
public class VocaController {
    private final VocaService vocaService;

    @GetMapping("/all/{fid}")
    public PageResponseDTO<VocaResponseDTO> getAll(@PathVariable(name = "fid") Long fid,
            PageRequestDTO pageRequestDTO) {
        return vocaService.getVocaListByFolder(fid, pageRequestDTO);
    }

    @GetMapping("/{vid}")
    public VocaResponseDTO get(@PathVariable(name = "vid") Long vid) {
        return vocaService.get(vid);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody VocaRequestDTO vocaRequestDTO) {
        Long vid = vocaService.createVoca(vocaRequestDTO).getVid();
        return Map.of("VID", vid);

    }

}
