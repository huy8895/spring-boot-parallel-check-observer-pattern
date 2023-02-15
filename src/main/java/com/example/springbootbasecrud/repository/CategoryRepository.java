package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseCRUDRepository<Category, Long>,
        JpaSpecificationExecutor<Category> {
}
