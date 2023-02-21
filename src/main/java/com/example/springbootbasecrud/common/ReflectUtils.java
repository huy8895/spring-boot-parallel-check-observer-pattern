package com.example.springbootbasecrud.common;

import com.example.springbootbasecrud.helper.excel.CellDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class ReflectUtils {
    public static <E> List<CellDTO> generateCellDTO(E entity) {
        Class<?> aClass = entity.getClass();
        Class<?> superclass = aClass.getSuperclass();

        List<Field> allField = new ArrayList<>();
        if (superclass != null) {
            allField.addAll(List.of(superclass.getDeclaredFields()));
        }
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


    public static <E> List<CellDTO> generateCellHeader(Class<E> aClass) {
        Class<?> superclass = aClass.getSuperclass();
        List<Field> allField = new ArrayList<>();
        if (superclass != null) {
            allField.addAll(List.of(superclass.getDeclaredFields()));
        }
        allField.addAll(List.of(aClass.getDeclaredFields()));

        AtomicInteger index = new AtomicInteger(0);
        LinkedList<CellDTO> cellDTOS = new LinkedList<>();
        try {
            for (Field declaredField : allField) {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                cellDTOS.add(CellDTO.builder()
                                    .index(index.getAndIncrement())
                                    .fieldName(fieldName)
                                    .build());
            }
        } catch (Exception e) {
            log.error("error getCellDTO: {}", e.getMessage());
            throw new RuntimeException("error getCellDTO");
        }

        return cellDTOS;
    }

    public static <E> List<Field> getAllField(Class<E> aClass) {
        return List.of(aClass.getFields());
    }

    public static <E> List<Method> getAllSetterMethod(Class<E> aClass) {
        return Arrays.stream(aClass.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).collect(
                Collectors.toList());
    }
}
