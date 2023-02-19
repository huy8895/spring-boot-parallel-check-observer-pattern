package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.base.BaseCRUDRepository;
import com.example.springbootbasecrud.entity.Upload;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends BaseCRUDRepository<Upload, Long> {
}
