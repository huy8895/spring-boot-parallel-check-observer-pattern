package com.example.springbootbasecrud.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadDTO  {
    public Long id;
    public String fileName;
    public String contentType;
    public long size;
    public byte[] data;
}
