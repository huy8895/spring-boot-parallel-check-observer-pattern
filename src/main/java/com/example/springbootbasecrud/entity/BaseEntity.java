package com.example.springbootbasecrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Slf4j
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @PrePersist
    public void onPrePersist() {
        final Date now = new Date();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
        this.deletedFlag = false;
    }

    @PreUpdate
    public void onPreUpdate(){
        final Date now = new Date();
        this.setUpdatedAt(now);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
