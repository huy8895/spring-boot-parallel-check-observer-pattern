package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductControllerAbstract extends AbstractBaseCRUDController<Product, ProductService> {

    protected ProductControllerAbstract(ProductService service) {
        super(service);
    }

    @GetMapping("/test")
    public void customizeFunction(){
        log.info("log : {}", "customizeFunction");
        service.customizeFunction();
    }
}