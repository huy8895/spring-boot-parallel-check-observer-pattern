package com.example.springbootbasecrud.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * A Upload.
 */

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@Table(name = "uploads")
public class Upload implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @Column(name = "deleted_flag")
    protected boolean deletedFlag;
    // TODO: 2/21/2023 deleteAt

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Lob
    @Column(name = "data", length = 1000)
    private byte[] data;

    //originalName
    //fileName = md5(originalName)
    //extension
    //contentType
    //size
    //data

    @Column(name = "content_type")
    private String contentType;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Upload)) {
            return false;
        }
        return id != null && id.equals(((Upload) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", name='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
