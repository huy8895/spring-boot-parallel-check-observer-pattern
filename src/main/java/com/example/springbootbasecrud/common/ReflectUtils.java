package com.example.springbootbasecrud.common;

import com.example.springbootbasecrud.helper.excel.CellDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ReflectUtils {
    public static <E> List<CellDTO> getCellDTO(E entity) {
        Class<?> aClass = entity.getClass();
        Class<?> superclass = aClass.getSuperclass();

        List<Field> allField = new ArrayList<>();
        if (superclass != null){
            allField.addAll(List.of(superclass.getDeclaredFields()));
        }
//        Field[] declaredFields = aClass.getDeclaredFields();
        allField.addAll(List.of(aClass.getDeclaredFields()));

        AtomicInteger index = new AtomicInteger(0);
        LinkedList<CellDTO> cellDTOS = new LinkedList<>();

        try {
            for (Field declaredField : allField) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(entity);
                String fieldName = declaredField.getName();
                if (value != null) {
                    cellDTOS.add(CellDTO.builder()
                                        .index(index.getAndIncrement())
                                        .value(value.toString())
                                        .fieldName(fieldName)
                                        .build());
                }
            }
        } catch (Exception e) {
            log.error("error getCellDTO: {}", e.getMessage());
            throw new RuntimeException("error getCellDTO");
        }

        return cellDTOS;
    }


    public static <E> List<CellDTO> ok(Class<E> eClass) {

        return null;
    }
}
