package com.example.springbootbasecrud.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO extends BaseCRUDDTO {
    public String name;
}
