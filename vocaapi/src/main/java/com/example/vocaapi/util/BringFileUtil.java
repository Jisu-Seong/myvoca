package com.example.vocaapi.util;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
public class BringFileUtil {
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
    public MultipartFile getProfileImage(Member member) {
        if (member.getFilename() == null) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");

        } else {
            String filename = member.getFilename();
            Resource resource = new FileSystemResource(uploadPath + File.separator + filename);
            if (!resource.isReadable()) {
                resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");
            }
        }

    }

    // 파일 찾는 메소드

}
