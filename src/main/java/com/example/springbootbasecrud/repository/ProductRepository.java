package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseCRUDRepository<Product, Long> , JpaSpecificationExecutor<Product> {
}
