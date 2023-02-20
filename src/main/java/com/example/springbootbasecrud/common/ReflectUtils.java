package com.example.springbootbasecrud.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class ReflectUtils<E> {
    public void getAllFields(E dto) {
        Class<?> aClass = dto.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object o = declaredField.get(dto);
                String fieldName = declaredField.getName();
                if (o instanceof String) {
                    String fieldValue = (String) o;
                } else if (o instanceof List && CollectionUtils.isEmpty((List) o)) {

                } else if (o == null) {

                }
            }
        } catch (IllegalAccessException e) {
            log.error("validAllFields: {}", e.getMessage());
        }
    }
}
