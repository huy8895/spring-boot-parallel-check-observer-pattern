package com.example.springbootbasecrud.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends BaseCRUDDTO {
    public String name;
}
