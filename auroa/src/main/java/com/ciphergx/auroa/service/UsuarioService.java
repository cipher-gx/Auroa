package com.ciphergx.auroa.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ciphergx.auroa.dto.ActualizarPasswordRequestDto;
import com.ciphergx.auroa.dto.RegistroRequestDto;
import com.ciphergx.auroa.dto.UsuarioResponseDto;
import com.ciphergx.auroa.model.Usuario;
import com.ciphergx.auroa.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto registrarUsuario(RegistroRequestDto dto){
        if(usuarioRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("El correo ya esta registrado");
        }
        String hash = passwordEncoder.encode(dto.getPassword());
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setNombre(dto.getNombre());
        usuario.setPasswordHash(hash);
        usuarioRepository.save(usuario);
        return new UsuarioResponseDto(usuario.getNombre(), usuario.getEmail(), usuario.getFechaRegistro(), usuario.getId());
    }

    public void actualizarPassword(Usuario usuario, ActualizarPasswordRequestDto dto){

        if (!passwordEncoder.matches(dto.getOldPassword(), usuario.getPasswordHash())){
            throw new RuntimeException("La contraseña es incorrecta");
        }

        String hash = passwordEncoder.encode(dto.getNewPassword());
        usuario.setPasswordHash(hash);
        usuarioRepository.save(usuario);
    }
}
