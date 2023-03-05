package com.example.springbootbasecrud.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterResDto {
    private String email;
    private String name;
    private String chucvu;
}
