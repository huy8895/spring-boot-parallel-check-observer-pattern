package com.example.springbootbasecrud.helper.excel.impl;

import com.example.springbootbasecrud.helper.excel.ExcelHelper;
import com.example.springbootbasecrud.helper.excel.ExcelPOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class ExcelHelperImpl<E extends ExcelPOJO> implements ExcelHelper<E> {
    @Override
    public List<E> readFile(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] writeFile(List<E> file) {
        return new byte[0];
    }
}
