package com.example.springbootbasecrud.helper.excel;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelHelper<E extends ExcelPOJO > {
    List<E> readFile(MultipartFile file);
    byte[] writeFile(List<E> file);
}
