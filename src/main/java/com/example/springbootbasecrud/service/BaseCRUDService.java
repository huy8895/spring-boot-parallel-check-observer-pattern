package com.example.springbootbasecrud.service;

import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseCRUDService<E extends BaseCRUDEntity> {
    /**
     * Save a baseCRUDEntity.
     *
     * @param baseCRUDEntity the entity to save.
     * @return the persisted entity.
     */
    E save(E baseCRUDEntity);

    /**
     * Updates a baseCRUDEntity.
     *
     * @param baseCRUDEntity the entity to update.
     * @return the persisted entity.
     */
    E update(E baseCRUDEntity);

    /**
     * Partially updates a baseCRUDEntity.
     *
     * @param baseCRUDEntity the entity to update partially.
     * @return the persisted entity.
     */
    Optional<E> partialUpdate(E baseCRUDEntity);

    /**
     * Get all the capacities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<E> findAll(Pageable pageable);

    /**
     * Get the "id" baseCRUDEntity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    E findOne(Long id);

    /**
     * Soft Delete the "id" baseCRUDEntity.
     * set deleted_flag to True
     * @param id the id of the entity.
     */
    void softDelete(Long id);

    /**
     * Hard Delete the "id" baseCRUDEntity.
     * remove from entity
     * @param id the id of the entity.
     */
    void hardDelete(Long id);
    
}
