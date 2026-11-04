package com.ciphergx.auroa.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ciphergx.auroa.dto.RegistroRequestDto;
import com.ciphergx.auroa.dto.UsuarioResponseDto;
import com.ciphergx.auroa.model.Usuario;
import com.ciphergx.auroa.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDto> obtenerPerfil(@AuthenticationPrincipal Usuario u) {

            UsuarioResponseDto udto = new UsuarioResponseDto(u.getNombre(), u.getEmail(), u.getFechaRegistro(), u.getId());
            return ResponseEntity.ok(udto);
        
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDto> registrarUsuario(@RequestBody RegistroRequestDto dto){
        UsuarioResponseDto respuesta = usuarioService.registrarUsuario(dto);
        return ResponseEntity.ok(respuesta);
    }
    
}