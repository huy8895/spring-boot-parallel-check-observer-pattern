package com.example.springbootbasecrud.base;

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
public class BaseCRUDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @Column(name = "updated_at")
    protected Date updatedAt;

    @Column(name = "deleted_flag")
    protected boolean deletedFlag;

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
        if (!(o instanceof BaseCRUDEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
