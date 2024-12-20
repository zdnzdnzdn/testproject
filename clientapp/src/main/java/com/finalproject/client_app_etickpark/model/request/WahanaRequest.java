package com.finalproject.client_app_etickpark.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WahanaRequest {
    private String nama;
    private String keterangan;
    private String harga;
    private MultipartFile gambar;
}
