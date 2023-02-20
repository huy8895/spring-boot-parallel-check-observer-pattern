package com.example.springbootbasecrud.entity;


import com.example.springbootbasecrud.base.BaseCRUDEntity;
import com.example.springbootbasecrud.helper.excel.CellDTO;
import com.example.springbootbasecrud.helper.excel.ExcelPOJO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseCRUDEntity implements ExcelPOJO {
    @Column(name = "name")
    public String name;

    @Column(name = "category_id")
    public Long categoryId;

    @Override
    public List<CellDTO> getCells() {
        return List.of(CellDTO.builder()
                              .index(0)
                              .value(String.valueOf(id))
                              .build());
    }
}
