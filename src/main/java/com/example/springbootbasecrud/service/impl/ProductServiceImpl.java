package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.repository.CategoryRepository;
import com.example.springbootbasecrud.repository.ProductRepository;
import com.example.springbootbasecrud.service.AbstractBaseCRUDService;
import com.example.springbootbasecrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl extends AbstractBaseCRUDService<Product, ProductRepository> implements ProductService {
    private ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository) {
        super(repository);
    }
}
