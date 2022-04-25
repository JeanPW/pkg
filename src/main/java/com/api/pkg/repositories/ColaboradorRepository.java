package com.api.pkg.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pkg.models.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID>{

	boolean existsByNome(String nome);
	
	boolean existsByCpf(Long cpf);
	
	boolean existsByEmail(String email);
}