package com.ciphergx.auroa.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class AuthResponseDto {
    private String token;
    private String nombre;
    private UUID id;

}
