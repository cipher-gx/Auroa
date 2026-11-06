package com.ciphergx.auroa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciphergx.auroa.dto.AuthResponseDto;
import com.ciphergx.auroa.dto.LoginRequestDto;
import com.ciphergx.auroa.dto.RestablecerPasswordRequestDto;
import com.ciphergx.auroa.dto.ValidarCodigoRequestDto;
import com.ciphergx.auroa.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> generarToken(@Valid @RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    // PASO 1: Solicitar el código al correo
    @PostMapping("/restablecer")
    public ResponseEntity<String> solicitarRecuperacion(@RequestParam String email) {
        authService.solicitarRecuperacion(email);
        return ResponseEntity.ok("Si el correo está registrado, recibirás un código de recuperación.");
    }

    // PASO 2: Validar que los 6 dígitos sean correctos antes de cambiar de pantalla
    @PostMapping("/validar-codigo")
    public ResponseEntity<String> validarCodigo(@Valid @RequestBody ValidarCodigoRequestDto dto) {
        authService.validarCodigoRecuperacion(dto.getEmail(), dto.getToken());
        return ResponseEntity.ok("Código válido.");
    }

    // PASO 3: Guardar la nueva contraseña y quemar el código
    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarPassword(@Valid @RequestBody RestablecerPasswordRequestDto dto) {
        authService.restablecerPassword(dto);
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }
}