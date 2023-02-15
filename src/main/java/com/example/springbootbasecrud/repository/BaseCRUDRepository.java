package com.example.springbootbasecrud.repository;

import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseCRUDRepository<E extends BaseCRUDEntity, ID> extends JpaRepository<E, ID> {
    Optional<E> findByIdAndDeletedFlagFalse(Long Id);
}
