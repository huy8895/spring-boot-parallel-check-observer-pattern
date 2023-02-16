package com.example.springbootbasecrud.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseCRUDRepository<E extends BaseCRUDEntity, ID> extends JpaRepository<E, ID> ,
        JpaSpecificationExecutor<E> {
    Optional<E> findByIdAndDeletedFlagFalse(Long Id);
}
