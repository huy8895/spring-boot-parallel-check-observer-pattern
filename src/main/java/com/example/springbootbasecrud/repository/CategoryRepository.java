package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.base.BaseCRUDRepository;
import com.example.springbootbasecrud.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseCRUDRepository<Category, Long> {
}
