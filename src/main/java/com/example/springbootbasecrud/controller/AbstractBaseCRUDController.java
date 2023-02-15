package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.dto.BaseCRUDDTO;
import com.example.springbootbasecrud.entity.BaseCRUDEntity;
import com.example.springbootbasecrud.service.BaseCRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class AbstractBaseCRUDController<
        E extends BaseCRUDEntity,
        DTO extends BaseCRUDDTO,
        S extends BaseCRUDService<E>> {
    protected final S service;

    protected AbstractBaseCRUDController(S service) {
        this.service = service;
    }
    @PostMapping
    public  ResponseEntity<DTO>  create(@RequestBody DTO requestDTO) {
//        return ResponseEntity.ok(service.save(entity));
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getDetail(@PathVariable Long id) {
//        return ResponseEntity.ok(service.findOne(id));
        return null;
    }

    @PutMapping
    public ResponseEntity<DTO> update(@RequestBody DTO requestDTO) {
//        return ResponseEntity.ok(service.update(entity));
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}