package com.example.vocaapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<VocaResponseDTO> getVocaListFolder(@PathVariable(name = "fid") Long fid) {
        return vocaService.getVocaList(fid);
    }

    @GetMapping("/{vid}")
    public VocaResponseDTO getVocaDetail(@PathVariable(name = "vid") Long vid) {
        return vocaService.getOneVoca(vid);
    }

    @GetMapping("/{vid}/tags")
    public List<String> getTagsOneVoca(@PathVariable(name = "vid") Long vid) {
        return vocaService.findAllTagsByVoca(vid);
    }

    @PostMapping("/add/{fid}")
    public Map<String, String> addVoca(@PathVariable(name = "fid") Long fid,
            @RequestBody VocaFormDTO vocaFormDTO) {
        VocaResponseDTO vocaResponseDTO = vocaService.addVoca(fid, vocaFormDTO.getVocaRequestDTO());
        vocaService.addTags(vocaFormDTO.getTags());
        vocaService.addRelation(vocaResponseDTO.getVid(), vocaFormDTO.getTags());
        return Map.of("RESULT", "SUCCESS");
    }

    @PutMapping("/{vid}/modify")
    public Map<String, String> modifyVoca(@PathVariable(name = "vid") Long vid, @RequestBody VocaFormDTO vocaFormDTO) {
        vocaService.deleteRelation(vid);
        vocaService.modifyVoca(vid, vocaFormDTO.getVocaRequestDTO());
        List<String> newTags = vocaService.compareTagList(vocaService.findAllTagsByVoca(vid), vocaFormDTO.getTags());
        vocaService.addTags(newTags);
        vocaService.addRelation(vid, vocaFormDTO.getTags());
        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{vid}")
    public Map<String, String> deleteVoca(@PathVariable(name = "vid") Long vid) {
        vocaService.deleteRelation(vid);
        vocaService.deleteVoca(vid);
        return Map.of("RESULT", "SUCCESS");
    }

}
