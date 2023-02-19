package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.base.AbstractBaseCRUDController;
import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.entity.Upload;
import com.example.springbootbasecrud.service.CategoryService;
import com.example.springbootbasecrud.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/uploads")
public class UploadController extends AbstractBaseCRUDController<Upload, UploadService> {
    protected UploadController(UploadService service) {
        super(service);
    }
}