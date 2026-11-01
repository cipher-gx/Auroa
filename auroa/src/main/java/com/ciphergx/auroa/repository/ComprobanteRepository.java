package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphergx.auroa.model.Comprobante;

public interface ComprobanteRepository extends JpaRepository<Comprobante, UUID> {
    
}
