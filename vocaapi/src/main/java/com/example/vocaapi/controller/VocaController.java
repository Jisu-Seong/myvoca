package com.example.vocaapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocaapi.dto.PageResponseDTO;
import com.example.vocaapi.dto.SortRequestDTO;
import com.example.vocaapi.dto.VocaFormDTO;
import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.service.VocaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voca")
@Transactional
public class VocaController {
    private final VocaService vocaService;

    @GetMapping("/list/{fid}")
    public ResponseEntity<PageResponseDTO<VocaResponseDTO>> getVocaListFolder(
            Principal principal,
            @PathVariable(name = "fid") Long fid,
            @RequestBody SortRequestDTO sortRequestDTO,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new ResponseEntity<>(
                vocaService.getVocaList(fid, principal,
                        sortRequestDTO, page, size),
                HttpStatus.OK);

    }

    @GetMapping("/{vid}")
    public VocaResponseDTO getVocaDetail(
            Principal principal,
            @PathVariable(name = "vid") Long vid) {
        return vocaService.getOneVoca(vid, principal);
    }

    @GetMapping("/{vid}/tags")
    public List<String> getTagsOneVoca(
            Principal principal,
            @PathVariable(name = "vid") Long vid) {
        return vocaService.findAllTagsByVoca(vid, principal);
    }

    @PostMapping("/add/{fid}")
    public Map<String, String> addVoca(
            Principal principal,
            @PathVariable(name = "fid") Long fid,
            @RequestBody VocaFormDTO vocaFormDTO) {
        VocaResponseDTO vocaResponseDTO = vocaService.addVoca(fid,
                vocaFormDTO.getVocaRequestDTO(), principal);
        vocaService.addTags(vocaFormDTO.getTags());
        vocaService.addRelation(vocaResponseDTO.getVid(), vocaFormDTO.getTags());
        return Map.of("RESULT", "SUCCESS");
    }

    @PutMapping("/{vid}/modify")
    public Map<String, String> modifyVoca(
            Principal principal,
            @PathVariable(name = "vid") Long vid,
            @RequestBody VocaFormDTO vocaFormDTO) {
        vocaService.deleteRelation(vid);
        vocaService.modifyVoca(vid, vocaFormDTO.getVocaRequestDTO(), principal);
        List<String> newTags = vocaService.compareTagList(vocaService.findAllTagsByVoca(vid, principal),
                vocaFormDTO.getTags());
        vocaService.addTags(newTags);
        vocaService.addRelation(vid, vocaFormDTO.getTags());
        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{vid}")
    public Map<String, String> deleteVoca(
            Principal principal,
            @PathVariable(name = "vid") Long vid) {
        vocaService.deleteRelation(vid);
        vocaService.deleteVoca(vid, principal);
        return Map.of("RESULT", "SUCCESS");
    }

}
