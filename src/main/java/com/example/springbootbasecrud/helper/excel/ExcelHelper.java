package com.example.springbootbasecrud.helper.excel;

import com.example.springbootbasecrud.common.ReflectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelHelper<E> {
    List<E> readFile(MultipartFile file);
    byte[] writeFile(List<E> file, Class<E> eClass);

    default List<CellDTO> getCellHeader(Class<E> eClass) {
        return ReflectUtils.generateCellHeader(eClass);
    }

    default List<CellDTO> getCellDTOS(E element) {
        return ReflectUtils.generateCellDTO(element);
    }
}
