package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.service.CategoryService;
import com.example.springbootbasecrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseCRUDController<Category, CategoryService> {

    protected CategoryController(CategoryService service) {
        super(service);
    }

}