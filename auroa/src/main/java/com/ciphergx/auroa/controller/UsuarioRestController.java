package com.ciphergx.auroa.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ciphergx.auroa.dto.UsuarioResponseDto;
import com.ciphergx.auroa.model.Usuario;
import com.ciphergx.auroa.repository.UsuarioRepository;

public class UsuarioRestController {
    
    UsuarioRepository usuarioRepository;

    @GetMapping 
    public ResponseEntity<UsuarioResponseDto> buscarPorCorreo(@RequestParam String email){
        Optional<Usuario> caja = usuarioRepository.findByEmail(email);
        if(caja.isPresent()){
            Usuario u = caja.get();
            UsuarioResponseDto udto = new UsuarioResponseDto(u.getNombre(), u.getEmail(), u.getFechaRegistro(), u.getId());
            return ResponseEntity.ok(udto);
        }
        return ResponseEntity.notFound().build();
    }
}
