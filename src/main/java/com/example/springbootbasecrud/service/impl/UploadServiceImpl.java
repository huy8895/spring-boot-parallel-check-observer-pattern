package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.base.AbstractBaseCRUDService;
import com.example.springbootbasecrud.entity.Upload;
import com.example.springbootbasecrud.repository.UploadRepository;
import com.example.springbootbasecrud.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UploadServiceImpl extends AbstractBaseCRUDService<Upload, UploadRepository> implements UploadService {
    protected UploadServiceImpl(UploadRepository repository) {
        super(repository);
    }
}
