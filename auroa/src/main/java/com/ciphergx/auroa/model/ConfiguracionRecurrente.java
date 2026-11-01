package com.ciphergx.auroa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table (name = "configuracion_recurrente")
@NoArgsConstructor 
@Getter 
@Setter 
public class ConfiguracionRecurrente {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private TipoMovimiento tipo;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 30)
    private Categoria categoria;

    @Column (nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column (nullable = false, length = 150)
    private String concepto;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private Periodicidad periodicidad;

    @Column (name = "proxima_ejecucion", nullable = false)
    private LocalDate proximaEjecucion;

    @Column (nullable = false)
    private Boolean activa = true;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
}