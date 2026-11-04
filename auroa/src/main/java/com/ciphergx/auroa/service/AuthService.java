package com.ciphergx.auroa.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ciphergx.auroa.dto.ActualizarPasswordRequestDto;
import com.ciphergx.auroa.dto.AuthResponseDto;
import com.ciphergx.auroa.dto.LoginRequestDto;
import com.ciphergx.auroa.repository.UsuarioRepository;
import com.ciphergx.auroa.model.Usuario;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor  
public class AuthService {

    public AuthResponseDto login(LoginRequestDto dto){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Credenciales invalidas");
        }
        if (!passwordEncoder.matches(dto.getPassword(), usuario.get().getPasswordHash())){
            throw new RuntimeException("Credenciales invalidas");
        }
        String token = jwtService.generarToken(usuario.get());

        return new AuthResponseDto(token, usuario.get().getNombre(), usuario.get().getId());
    }

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
}
