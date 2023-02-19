package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.dto.UploadDTO;
import com.example.springbootbasecrud.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(service.upload(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id){
        UploadDTO dto =service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.valueOf(dto.getContentType()))
                             .body(dto.getData());

    }
}