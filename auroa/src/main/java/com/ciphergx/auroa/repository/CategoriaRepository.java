package com.ciphergx.auroa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphergx.auroa.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID>{

}
