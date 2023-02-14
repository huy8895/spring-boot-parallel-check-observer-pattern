package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product save(Product baseCRUDEntity) {
        return null;
    }

    @Override
    public Product update(Product baseCRUDEntity) {
        return null;
    }

    @Override
    public Optional<Product> partialUpdate(Product baseCRUDEntity) {
        return Optional.empty();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void softDelete(Long id) {

    }

    @Override
    public void hardDelete(Long id) {

    }
}
