package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
}
