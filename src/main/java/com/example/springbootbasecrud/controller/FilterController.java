package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.dto.FilterRequestDto;
import com.example.springbootbasecrud.dto.FilterResDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/filter")
@RestController
public class FilterController {

    @PostMapping
    public FilterResDto baseFilter(@RequestBody FilterRequestDto dto){
        return new FilterResDto();
    }
}
