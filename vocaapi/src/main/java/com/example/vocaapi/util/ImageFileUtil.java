package com.example.vocaapi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.vocaapi.entity.Member;
import com.example.vocaapi.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class ImageFileUtil {
    private final MemberRepository memberRepository;

    @Value("${upload.path.profileImg}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);

        if (tempFolder.exists() == false) {
            tempFolder.mkdir();
        }
        uploadPath = tempFolder.getAbsolutePath();

        log.info("-----------------------");
        log.info(uploadPath);
    }

    // db에 저장되어있는 multipart string이 없을때
    // resource에 있는 default 이미지를 리턴

    // 있으면 그 이름에 해당하는 이미지를 리턴
    public ResponseEntity<Resource> getImage(Member member) {
        String filename = null;
        Resource resource = null;
        if (member.getFilename() == null) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");

        } else {
            filename = member.getFilename();
            resource = new FileSystemResource(uploadPath + File.separator + filename);
            if (!resource.isReadable()) {
                resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");
            }
        }
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    public String saveImage(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path savePath = Paths.get(uploadPath, savedName);

        String contentType = file.getContentType();

        if (contentType != null && contentType.startsWith("image")) {
            try {
                Files.copy(file.getInputStream(), savePath);

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

        }
        return savedName;
    }

    public void deleteImage(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return;
        }
        Path filePath = Paths.get(uploadPath, fileName);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
