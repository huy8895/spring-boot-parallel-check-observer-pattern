package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import com.example.springbootbasecrud.service.BaseCRUDService;
import org.springframework.http.ResponseEntity;


public abstract class BaseCRUDController<E extends BaseCRUDEntity, S extends BaseCRUDService<E>> {
     protected final S service;

    protected BaseCRUDController(S service) {
        this.service = service;
    }

    public ResponseEntity<E> create(E entity){
        return ResponseEntity.ok(service.save(entity));
    };

    public ResponseEntity<E> update(E entity){
        return ResponseEntity.ok(service.update(entity));
    };

    public ResponseEntity<Void> delete(Long id){
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    };
}