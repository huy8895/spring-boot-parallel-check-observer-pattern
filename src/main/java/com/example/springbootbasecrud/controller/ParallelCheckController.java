package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.service.check.ParallelCheckService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/api/parallelchecks")
@RequiredArgsConstructor
public class ParallelCheckController {
	private final ParallelCheckService service;
    
    @GetMapping
	public Object check(){
	    return service.check();
    }
}