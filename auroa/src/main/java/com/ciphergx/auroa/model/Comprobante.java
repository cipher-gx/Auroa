package com.ciphergx.auroa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "comprobantes") 
@NoArgsConstructor 
@Getter 
@Setter 
public class Comprobante {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, unique = true)
    private String archivoUrl;

    @Column (nullable = false)
    private String formato;

    @Column (nullable = false, unique = true)
    private String hashSha256;

    @Column (unique = true) 
    private String folioUuid;

    @Column (nullable = false)
    private String proveedorExtraido;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal montoExtraido;

    @Column (nullable = false)
    private LocalDate fechaExtraida;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private EstadoComprobante estado = EstadoComprobante.BORRADOR;
}