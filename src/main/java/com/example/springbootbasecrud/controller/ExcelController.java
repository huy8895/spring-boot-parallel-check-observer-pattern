package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.entity.Category;
import com.example.springbootbasecrud.entity.Product;
import com.example.springbootbasecrud.helper.excel.ExcelHelper;
import com.example.springbootbasecrud.service.CategoryService;
import com.example.springbootbasecrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelHelper excelHelper;
    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/export/product")
    public ResponseEntity<?> export() {
        Page<Product> productPage = productService.findAll(Pageable.ofSize(10));
        byte[] writeFile = excelHelper.writeFile(productPage.getContent(), Product.class);
        return ResponseEntity.ok(writeFile);
    }

    @PostMapping(value = "/import/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importProduct(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(excelHelper.readFile(file, Product.class));
    }

    @GetMapping("/export/category")
    public ResponseEntity<?> exportCategory() {
        Page<Category> productPage = categoryService.findAll(Pageable.ofSize(10));
        byte[] writeFile = excelHelper.writeFile(productPage.getContent(), Category.class);
        return ResponseEntity.ok(writeFile);
    }
}
