package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphergx.auroa.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID>{

}
