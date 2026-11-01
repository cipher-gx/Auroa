package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracionRecurrente extends JpaRepository<ConfiguracionRecurrente, UUID> {
    
}
