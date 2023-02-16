package com.example.springbootbasecrud.entity;


import com.example.springbootbasecrud.base.BaseCRUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseCRUDEntity {
    @Column(name = "name")
    public String name;

    @Column(name = "category_id")
    public Long categoryId;
}
