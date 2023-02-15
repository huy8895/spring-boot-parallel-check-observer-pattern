package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import com.example.springbootbasecrud.service.BaseCRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class AbstractBaseCRUDController<E extends BaseCRUDEntity, S extends BaseCRUDService<E>> {
    protected final S service;

    protected AbstractBaseCRUDController(S service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<E> create(@RequestBody E entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping
    public ResponseEntity<E> update(@RequestBody E entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}