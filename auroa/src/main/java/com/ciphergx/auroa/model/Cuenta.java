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
@Table (name = "cuentas")
@NoArgsConstructor 
@Getter
@Setter 
public class Cuenta {
     
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false)
    @Enumerated (EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column (nullable = false, length = 3)
    private String divisa = "MXN";

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoActual = BigDecimal.ZERO;

    @Column (precision = 15, scale = 2)
    private BigDecimal limiteCredito;

    @Column (precision = 15, scale = 2)
    private BigDecimal saldoAFavor = BigDecimal.ZERO;

    @Column 
    private Integer diaCorte;

    @Column 
    private Integer diaLimitePago;

    @Column (nullable = false)
    private Boolean activa = true;

    @JoinColumn(name = "usuario_id", nullable = false)
    @ManyToOne (fetch = FetchType.LAZY)
    private Usuario usuario;

}
