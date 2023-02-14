package com.example.springbootbasecrud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class HeathController {

    @GetMapping
    public Object ping(){
        log.info("ping ");
        return "OK";
    }



}