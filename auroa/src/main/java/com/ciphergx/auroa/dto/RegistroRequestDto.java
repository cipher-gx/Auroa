package com.ciphergx.auroa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class RegistroRequestDto {
    
    private String nombre;
    private String password;
    private String email;
}
