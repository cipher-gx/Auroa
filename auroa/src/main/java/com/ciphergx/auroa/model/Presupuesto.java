package com.ciphergx.auroa.model;

import java.math.BigDecimal;
import java.time.YearMonth;
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
@Table (name = "presupuestos")
@Getter 
@Setter 
@NoArgsConstructor 
public class Presupuesto {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Categoria categoria;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal limiteMensual;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal montoConsumido = BigDecimal.ZERO;
    
    @Column (nullable = false)
    private YearMonth mesAnio;

    @JoinColumn (name = "usuario_id", nullable = false)
    @ManyToOne (fetch = FetchType.LAZY)
    private Usuario usuario;
}
