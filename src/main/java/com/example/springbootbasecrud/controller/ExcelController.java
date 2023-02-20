package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.helper.excel.ExcelHelper;
import com.example.springbootbasecrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelHelper<Product> excelHelper;
    private final ProductService productService;


    @GetMapping("/product")
    public ResponseEntity<?> export(){
        Page<Product> productPage = productService.findAll(Pageable.ofSize(10));
        byte[] writeFile = excelHelper.writeFile(productPage.getContent(), Product.class);
        return ResponseEntity.ok(writeFile)
                ;
    }
}
