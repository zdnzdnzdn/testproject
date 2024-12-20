package com.finalproject.client_app_etickpark.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wahana { 
    private Integer id;
    private String nama; 
    private String keterangan; 
    private Integer harga; 
    private String gambar;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<TiketDetail> tiketDetails;
}