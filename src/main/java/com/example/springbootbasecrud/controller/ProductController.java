package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseCRUDController<Product, ProductService> {

    protected ProductController(ProductService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product entity) {
        log.info("create entity : {}", entity);
        return super.create(entity);
    }

    @Override
    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product entity) {
        log.info("update entity : {}", entity);
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("delete id : {}", id);
        return super.delete(id);
    }

    @GetMapping("/test")
    public void customizeFunction(){
        log.info("log : {}", "customizeFunction");
        service.customizeFunction();
    }
}