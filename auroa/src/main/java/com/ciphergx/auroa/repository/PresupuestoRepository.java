package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphergx.auroa.model.Presupuesto;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, UUID> {

}
