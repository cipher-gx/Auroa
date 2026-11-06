package com.ciphergx.auroa.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ciphergx.auroa.dto.AuthResponseDto;
import com.ciphergx.auroa.dto.LoginRequestDto;
import com.ciphergx.auroa.dto.RestablecerPasswordRequestDto;
import com.ciphergx.auroa.repository.TokenRecuperacionRepository;
import com.ciphergx.auroa.repository.UsuarioRepository;
import com.ciphergx.auroa.model.TokenRecuperacion;
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

    public void solicitarRecuperacion(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            return;
        }
        
        LocalDateTime haceUnDia = LocalDateTime.now().minusHours(24);
        long intentosHoy = tokenRecuperacionRepository
                .countByUsuarioEmailAndFechaExpiracionAfter(email, haceUnDia);

        if (intentosHoy >= 3) {
            throw new RuntimeException("Has alcanzado el límite máximo de 3 solicitudes por día. Intenta mañana.");
        }

        tokenRecuperacionRepository.findByUsuarioEmailAndUsadoFalse(email)
                .ifPresent(tokenViejo -> {
                    tokenViejo.setUsado(true);
                    tokenRecuperacionRepository.save(tokenViejo);
                });

        String codigoLimpio = generarCodigoAlfanumerico();

        TokenRecuperacion tokenRecuperacion = new TokenRecuperacion();
        tokenRecuperacion.setUsuario(usuario.get());
        tokenRecuperacion.setToken(passwordEncoder.encode(codigoLimpio));
        tokenRecuperacion.setFechaExpiracion(LocalDateTime.now().plusMinutes(15));

        tokenRecuperacionRepository.save(tokenRecuperacion);
        emailService.enviarCorreoRecuperacion(usuario.get().getEmail(), codigoLimpio);
    }

    public void validarCodigoRecuperacion(String email, String codigo) {
        Optional<TokenRecuperacion> tokenOpt = tokenRecuperacionRepository.findByUsuarioEmailAndUsadoFalse(email);
        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("Código inválido o expirado");
        }

        TokenRecuperacion tokenR = tokenOpt.get();

        //Primero verificamos si ya expiró por tiempo
        if (LocalDateTime.now().isAfter(tokenR.getFechaExpiracion())) {
            tokenR.setUsado(true);
            tokenRecuperacionRepository.save(tokenR);
            throw new RuntimeException("El código ha expirado");
        }

        //Si el código no coincide, sumamos un intento fallido
        if (codigo == null || !passwordEncoder.matches(codigo.toUpperCase(), tokenR.getToken())) {
            int nuevosIntentos = tokenR.getIntentosFallidos() + 1;
            tokenR.setIntentosFallidos(nuevosIntentos);

            // Al llegar a 3 errores, quemamos el código definitivamente
            if (nuevosIntentos >= 3) {
                tokenR.setUsado(true);
                tokenRecuperacionRepository.save(tokenR);
                throw new RuntimeException("Demasiados intentos fallidos. El código ha sido bloqueado.");
            }

            tokenRecuperacionRepository.save(tokenR);
            throw new RuntimeException("Código inválido. Te quedan " + (3 - nuevosIntentos) + " intentos.");
        }
    }

    public void restablecerPassword(RestablecerPasswordRequestDto dto) {
        validarCodigoRecuperacion(dto.getEmail(), dto.getToken());

        TokenRecuperacion tokenR = tokenRecuperacionRepository.findByUsuarioEmailAndUsadoFalse(dto.getEmail()).get();
        Usuario usuario = tokenR.getUsuario();

        // Evitamos que ponga la misma contraseña que ya tenía antes
        if (passwordEncoder.matches(dto.getNewPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("La nueva contraseña no puede ser igual a la actual");
        }

        usuario.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        tokenR.setUsado(true);

        usuarioRepository.save(usuario);
        tokenRecuperacionRepository.save(tokenR);
    }

    private String generarCodigoAlfanumerico() {
        String caracteres = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder(6);
        
        for (int i = 0; i < 6; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo.toString(); // Ejemplo: "8K4P9X"
    }

    private final EmailService emailService;
    private final TokenRecuperacionRepository tokenRecuperacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
}
