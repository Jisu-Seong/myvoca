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
import com.example.vocaapi.entity.Tag;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.service.VocaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voca")
@Log4j2
public class VocaController {
    private final VocaService vocaService;

    @GetMapping("/list/{foldername}")
    public ResponseEntity<PageResponseDTO<VocaResponseDTO>> getVocaAllList(
            Principal principal,
            @PathVariable(name = "foldername") String foldername,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "updated_At") String sort) {
        return new ResponseEntity<>(vocaService.getVocaList(principal, foldername, sort,
                page, size), HttpStatus.OK);
    }

    @GetMapping("/listall")
    public ResponseEntity<PageResponseDTO<VocaResponseDTO>> getVocaAllFolderList(
            Principal principal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "updated_At") String sort) {
        return new ResponseEntity<>(vocaService.getVocaListAll(principal, sort, page, size), HttpStatus.OK);
    }

    @GetMapping("/get/{vid}")
    public VocaResponseDTO getVocaDetail(
            Principal principal,
            @PathVariable(name = "vid") Long vid) {
        return vocaService.getOneVoca(vid, principal);
    }

    @PostMapping("/add/{fid}")
    public Map<String, Object> addVoca(
            Principal principal,
            @PathVariable(name = "fid") Long fid,
            @RequestBody VocaFormDTO vocaFormDTO) {
        log.info("form responseDTO ========================");
        log.info(vocaFormDTO);
        List<Tag> tags = vocaService.addTags(vocaFormDTO.getTags());
        Vocabulary voca = vocaService.addVoca(fid,
                vocaFormDTO.getVocaRequestDTO(), tags, principal);
        VocaResponseDTO vocaResponseDTO = VocaResponseDTO.of(voca);
        log.info("보카 responseDTO ========================");
        log.info(vocaResponseDTO);
        vocaResponseDTO.setTags(vocaFormDTO.getTags());
        return Map.of("vocaResponseDTO", vocaResponseDTO);
    }

    @PutMapping("/modify/{vid}")
    public Map<String, String> modifyVoca(
            Principal principal,
            @PathVariable(name = "vid") Long vid,
            @RequestBody VocaFormDTO vocaFormDTO) {
        Vocabulary v = vocaService.modifyVoca(vid, vocaFormDTO.getVocaRequestDTO(),
                principal);
        vocaService.addTags(vocaFormDTO.getTags());
        return Map.of("RESULT", "SUCCESS");
    }

    // @DeleteMapping("/delete/{vid}")
    // public Map<String, String> deleteVoca(
    // Principal principal,
    // @PathVariable(name = "vid") Long vid) {
    // vocaService.deleteRelation(vid);
    // vocaService.deleteVoca(vid, principal);
    // return Map.of("RESULT", "SUCCESS");
    // }

    // @GetMapping("/tags/{vid}")
    // public List<String> getTagsOneVoca(
    // Principal principal,
    // @PathVariable(name = "vid") Long vid) {
    // return vocaService.findAllTagsByVoca(vid, principal);
    // }
}
