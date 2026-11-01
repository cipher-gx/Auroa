package com.ciphergx.auroa.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ciphergx.auroa.model.TokenRecuperacion;

public interface TokenRecuperacionRepository extends JpaRepository<TokenRecuperacion,UUID>{
    
}
