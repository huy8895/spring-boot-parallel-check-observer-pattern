package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);

        Product result = service.save(product);
        return ResponseEntity
                .created(new URI("/api/products/" + result.getId()))
                .body(result);
    }
}