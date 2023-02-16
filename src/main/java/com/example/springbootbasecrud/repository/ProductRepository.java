package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.base.BaseCRUDRepository;
import com.example.springbootbasecrud.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseCRUDRepository<Product, Long> {
}
