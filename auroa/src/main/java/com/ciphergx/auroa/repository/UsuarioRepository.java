package com.ciphergx.auroa.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.ciphergx.auroa.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
    Optional<Usuario> findByEmail(String email);
    
    
}
