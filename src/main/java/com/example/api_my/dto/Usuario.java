package com.example.api_my.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

}
