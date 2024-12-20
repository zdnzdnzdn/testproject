package com.finalproject.client_app_etickpark.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TiketDetail {   
    private Integer id;
    private String nama; 
    private Integer harga;
    private Integer jumlah;
    private LocalDateTime waktu; 
    private LocalDateTime created_at; 
    private CartDetail cartDetail;
    private Wahana wahana;
}