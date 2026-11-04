package com.ciphergx.auroa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ciphergx.auroa.dto.AuthResponseDto;
import com.ciphergx.auroa.dto.LoginRequestDto;
import com.ciphergx.auroa.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/auth")
@RequiredArgsConstructor 

public class AuthRestController {
    
    private final AuthService authService;

    @PostMapping("/login")
    
    public ResponseEntity<AuthResponseDto> generarToken(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}
