package com.example.springbootbasecrud.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadDTO  {
    public Long id;
    public String name;
    public String contentType;
    public String size;
    public byte[] data;
}
