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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "movimientos")
@NoArgsConstructor
@Getter 
@Setter 
public class Movimiento {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false, length = 30)
    private TipoMovimiento tipoMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Categoria categoria;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 100)
    private String concepto;

    @Column(length = 255)
    private String descripcion;

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_origen_id")
    private Movimiento movimientoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_meses_id")
    private CompraMeses compraMeses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deuda_favor_id")
    private DeudaFavor deudaFavor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_destino_id")
    private Cuenta cuentaDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuracion_recurrente_id")
    private ConfiguracionRecurrente configuracionRecurrente;
}