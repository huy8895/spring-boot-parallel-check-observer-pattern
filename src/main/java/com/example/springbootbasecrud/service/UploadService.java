package com.example.springbootbasecrud.service;

import com.example.springbootbasecrud.dto.UploadDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    UploadDTO upload(MultipartFile multipartFile) throws IOException;


    UploadDTO downloadImage(Long id);
}
