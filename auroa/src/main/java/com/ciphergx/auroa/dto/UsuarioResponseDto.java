package com.ciphergx.auroa.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
public class UsuarioResponseDto {
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;
    private UUID id;
}
