package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphergx.auroa.model.ConfiguracionRecurrente;

public interface ConfiguracionRecurrenteRepository extends JpaRepository<ConfiguracionRecurrente, UUID> {
    
}
