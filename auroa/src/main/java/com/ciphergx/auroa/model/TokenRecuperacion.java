package com.ciphergx.auroa.model;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "token_recuperacion")
@NoArgsConstructor 
@Getter 
@Setter 
public class TokenRecuperacion {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, unique = false)
    private String token;

    @Column (nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column (nullable = false)
    private Boolean usado = false;

    @JoinColumn(name = "usuario_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY) 
    private Usuario usuario;

    @Column(nullable = false)
    private Integer intentosFallidos = 0;
}
