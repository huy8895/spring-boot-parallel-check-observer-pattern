package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.base.AbstractBaseCRUDService;
import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.repository.CategoryRepository;
import com.example.springbootbasecrud.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl extends AbstractBaseCRUDService<Category, CategoryRepository> implements CategoryService {
    protected CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}
