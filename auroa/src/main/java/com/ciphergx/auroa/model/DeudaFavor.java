package com.ciphergx.auroa.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table (name = "deuda_favor")
@NoArgsConstructor 
@Getter 
@Setter 
public class DeudaFavor {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, length = 100)
    private String nombreDeudor;

    @Column (nullable = false, length = 100)
    private String concepto;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal montoOriginal;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoPendiente;

    @Column (nullable = false)
    @Enumerated (EnumType.STRING)
    private EstadoDeuda estadoDeuda = EstadoDeuda.ACTIVA;

    @JoinColumn (name = "usuario_id",nullable = false)
    @ManyToOne (fetch = FetchType.LAZY)
    private Usuario usuario;
}
