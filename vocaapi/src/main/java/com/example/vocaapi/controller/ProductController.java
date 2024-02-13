// package com.example.vocaapi.controller;

// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.Resource;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.vocaapi.dto.PageRequestDTO;
// import com.example.vocaapi.dto.PageResponseDTO;
// import com.example.vocaapi.dto.ProductDTO;
// import com.example.vocaapi.service.ProductService;
// import com.example.vocaapi.util.CustomFileUtil;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @RestController
// @RequiredArgsConstructor
// @Log4j2
// @RequestMapping("/api/products")
// public class ProductController {
// @Autowired
// private final ProductService productService;
// @Autowired
// private final CustomFileUtil fileUtil;

// @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // 임시로 권한 설정
// @GetMapping("/list")
// public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
// log.info("list............." + pageRequestDTO);
// return productService.getList(pageRequestDTO);
// }

// @PostMapping("/")
// public Map<String, Long> register(ProductDTO productDTO) {
// log.info("rgister: " + productDTO);
// List<MultipartFile> files = productDTO.getFiles();
// List<String> uploadFileNames = fileUtil.saveFiles(files);
// productDTO.setUploadFileNames(uploadFileNames);
// log.info(uploadFileNames);

// // 서비스 호출
// Long pno = productService.register(productDTO);

// try {
// Thread.sleep(2000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }

// return Map.of("result", pno);
// }

// @GetMapping("/view/{fileName}")
// public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
// return fileUtil.getFile(fileName);
// }

// @GetMapping("/{pno}")
// public ProductDTO read(@PathVariable(name = "pno") Long pno) {
// return productService.get(pno);
// }

// @PutMapping("/{pno}")
// public Map<String, String> modify(@PathVariable(name = "pno") Long pno,
// ProductDTO productDTO) {
// productDTO.setPno(pno);
// ProductDTO oldProductDTO = productService.get(pno);

// // 기존의 파일들(데이터베이스에 존재하는 파일들 - 수정 과정에서 삭제되었을 수도 있음)
// List<String> oldFileNames = oldProductDTO.getUploadFileNames();

// // 새로 업로드해야 하는 파일들
// List<MultipartFile> files = productDTO.getFiles();

// // 새로 업로드되어서 만들어진 파일 이름들
// List<String> currentUploadFileNames = fileUtil.saveFiles(files);

// // 화면에서 변화 없이 계속 유지된 파일들
// List<String> uploadedFileNames = productDTO.getUploadFileNames();

// // 유지되는 파일들 + 새로 업로드된 파일 이름이 저장해야 하는 파일 목록이 됨
// if (currentUploadFileNames != null && currentUploadFileNames.size() > 0) {
// uploadedFileNames.addAll(currentUploadFileNames);
// }

// // 수정 작업
// productService.modify(productDTO);

// if (oldFileNames != null && oldFileNames.size() > 0) {
// // 지워야 하는 파일 목록 찾기
// // 예전 파일들 중에서 지워야 하는 파일이름들
// List<String> removeFiles = oldFileNames
// .stream()
// .filter(fileName -> uploadedFileNames.indexOf(fileName) ==
// -1).collect(Collectors.toList());

// // 실제 파일 삭제
// fileUtil.deleteFiles(removeFiles);
// }
// return Map.of("RESULT", "SUCCESS");
// }

// @DeleteMapping("/{pno}")
// public Map<String, String> remove(@PathVariable("pno") Long pno) {
// // 삭제해야할 파일들 알아내기
// List<String> oldFileNames = productService.get(pno).getUploadFileNames();

// productService.remove(pno);

// fileUtil.deleteFiles(oldFileNames);
// return Map.of("RESULT", "SUCCESS");

// }

// }
