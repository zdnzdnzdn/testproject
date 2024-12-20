package com.finalproject.client_app_etickpark.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
   
    private Integer id;
    private String email;
    private String nama;
    private String telepon;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private User user;
    private List<CartDetail> cartDetails;
}