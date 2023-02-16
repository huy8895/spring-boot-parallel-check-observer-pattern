package com.example.springbootbasecrud.service;

import com.example.springbootbasecrud.base.BaseCRUDService;
import com.example.springbootbasecrud.entity.Product;

public interface ProductService extends BaseCRUDService<Product> {
    void customizeFunction();
}
