package com.finalproject.client_app_etickpark.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Privilege {
    private Integer id;
    private String nama;
    private List<Role> roles;
}