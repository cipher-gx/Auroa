package com.ciphergx.auroa.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false, length = 100, unique = true)
    private String email;

    @Column (nullable = false)
    private String passwordHash;

    @Column (nullable = false)
    private LocalDateTime fechaRegistro;
}
