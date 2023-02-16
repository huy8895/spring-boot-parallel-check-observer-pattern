package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.base.AbstractBaseCRUDController;
import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryControllerAbstract extends AbstractBaseCRUDController<Category, CategoryService> {

    protected CategoryControllerAbstract(CategoryService service) {
        super(service);
    }

}