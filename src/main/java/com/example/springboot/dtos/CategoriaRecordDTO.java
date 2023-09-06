package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRecordDTO(@NotBlank String nome) {
}
