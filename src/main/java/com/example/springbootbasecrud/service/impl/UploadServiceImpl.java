package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.common.FileUtils;
import com.example.springbootbasecrud.dto.UploadDTO;
import com.example.springbootbasecrud.entity.Upload;
import com.example.springbootbasecrud.repository.UploadRepository;
import com.example.springbootbasecrud.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl  implements UploadService {
    private final UploadRepository repository;

    @Override
    public UploadDTO upload(MultipartFile multipartFile) throws IOException {
        String contentType = multipartFile.getContentType();
        if (contentType == null) throw new RuntimeException("UploadServiceImpl upload invalid contenty");
        Upload upload = Upload.builder()
                              .contentType(contentType)
                              .fileName(getFileName(multipartFile))
                              .deleteAt(null)
                              .data(FileUtils.compress(multipartFile.getBytes()))
                              .createdAt(new Date())
                              .build();
        Upload saveUpload = repository.save(upload);
        return UploadDTO.builder()
                        .id(upload.getId())
                        .contentType(contentType)
                        .fileName(saveUpload.getFileName())
                        .build();
    }

    @Override
    public UploadDTO downloadImage(Long id) {
        final Upload upload = repository.findById(id)
                                        .orElseThrow();
        byte[] data = FileUtils.decompress(upload.getData());
        return UploadDTO.builder()
                .id(upload.getId())
                .data(data)
                .contentType(upload.getContentType())
                .fileName(upload.getFileName())
                .size(data.length)
                .build();
    }

    private String getFileName(MultipartFile multipartFile) {
        String filenameExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return "upload." + filenameExtension;
    }
}
