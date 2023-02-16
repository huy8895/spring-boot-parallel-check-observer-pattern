package com.example.springbootbasecrud.base;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BaseCrudUtils {

    private static final List<String> DONT_COPY_FIELD =
            List.of("id",
            "createdAt",
            "updatedAt",
                    "deletedFlag");
    public static Set<String> getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames;
    }

    // then use Spring BeanUtils to copy and ignore null using our function
    public static void update(Object src, Object target) {

        final var nullPropertyNames = getNullPropertyNames(src);
        nullPropertyNames.addAll(DONT_COPY_FIELD);

        BeanUtils.copyProperties(src, target, nullPropertyNames.toArray(String[]::new));
    }
}
