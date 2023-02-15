package com.example.springbootbasecrud.service;

import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import com.example.springbootbasecrud.repository.BaseCRUDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Slf4j
public abstract class AbstractBaseCRUDService<E extends BaseCRUDEntity, R extends BaseCRUDRepository<E, Long>> implements BaseCRUDService<E> {
    protected final R repository;

    protected AbstractBaseCRUDService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E baseCRUDEntity) {
        log.info("save baseCRUDEntity : {}", baseCRUDEntity);
        return repository.save(baseCRUDEntity);
    }

    @Override
    public E update(E baseCRUDEntity) {
        log.info("update baseCRUDEntity : {}", baseCRUDEntity);
        return repository.save(baseCRUDEntity);
    }

    @Override
    public Optional<E> partialUpdate(E baseCRUDEntity) {
        log.info("baseCRUDEntity : {}", baseCRUDEntity);
        return repository
                .findById(baseCRUDEntity.getId())
                .map(existingProduct -> {
//                    if (product.getName() != null) {
//                        existingProduct.setName(product.getName());
//                    }

                    return existingProduct;
                })
                .map(repository::save);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        log.info("findAll pageable : {}", pageable);
        return repository.findAll(pageable);
    }

    @Override
    public E findOne(Long id) {
        log.info("findOne id : {}", id);
        return repository.findByIdAndDeletedFlagFalse(id)
                         .orElseThrow();
    }

    @Override
    public void softDelete(Long id) {
        log.info("softDelete id : {}", id);
        repository.findById(id)
                .ifPresent(e -> {
                    e.setDeletedFlag(true);
                    repository.save(e);
                });
    }

    @Override
    public void hardDelete(Long id) {
        log.info("hardDelete id : {}", id);
        repository.deleteById(id);
    }
}
