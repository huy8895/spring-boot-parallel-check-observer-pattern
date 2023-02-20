package com.example.springbootbasecrud.base;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class AbstractBaseCRUDController<E extends BaseCRUDEntity, S extends BaseCRUDService<E>> {
    protected final S service;

    protected AbstractBaseCRUDController(S service) {
        this.service = service;
    }
    @PostMapping
    @Operation(description = "Tạo mới")
    public ResponseEntity<E> create(@RequestBody E entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    @GetMapping("/{id}")
    @Operation(description = "Xem chi tiết")
    public ResponseEntity<E> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping
    @Operation(description = "Xem danh sách")
    public ResponseEntity<Page<E>> getList(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping
    @Operation(description = "Cập nhật")
    public ResponseEntity<E> update(@RequestBody E entity) {
        return ResponseEntity.ok(service.partialUpdate(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Xóa")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}