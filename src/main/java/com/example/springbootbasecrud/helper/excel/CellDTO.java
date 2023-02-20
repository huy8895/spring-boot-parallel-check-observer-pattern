package com.example.springbootbasecrud.helper.excel;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CellDTO {
    private int index;
    private String value;
    private String fieldName;
}
