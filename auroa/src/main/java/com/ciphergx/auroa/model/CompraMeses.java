package com.ciphergx.auroa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table (name = "compras_meses")
@Getter 
@Setter 
@NoArgsConstructor 
public class CompraMeses {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, length = 100)
    private String concepto;

    @Column (name = "monto_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoTotal;

    @Column (name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column (name = "monto_mensualidad", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoMensualidad;

    @Column (name = "meses_pagados", nullable = false)
    private Integer mesesPagados = 0;

    @Column (name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column (nullable = false)
    private Boolean activa = true;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
}