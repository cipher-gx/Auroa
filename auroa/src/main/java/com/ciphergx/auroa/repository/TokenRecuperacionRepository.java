package com.ciphergx.auroa.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ciphergx.auroa.model.TokenRecuperacion;

public interface TokenRecuperacionRepository extends JpaRepository<TokenRecuperacion, UUID> {
    
    Optional<TokenRecuperacion> findByUsuarioEmailAndUsadoFalse(String email);
    long countByUsuarioEmailAndFechaExpiracionAfter(String email, LocalDateTime fechaLimite);
}