package com.finalproject.client_app_etickpark.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartDetail {

    private Integer id;
    private LocalDateTime waktu;
    private Integer harga;
    private Integer jumlah;
    private Customer customer;
    private List<TiketDetail> tiketDetails;
}