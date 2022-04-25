package com.api.pkg.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pkg.models.Colaborador;
import com.api.pkg.repositories.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public Colaborador save(Colaborador colaborador) {
		return colaboradorRepository.save(colaborador);
	}
	
	public boolean existsByNome(String nome) {
		return colaboradorRepository.existsByNome(nome);
	}
	public boolean existsByCpf(Long cpf) {
		return colaboradorRepository.existsByCpf(cpf);
	}
	public boolean existsByEmail(String email) {
		return colaboradorRepository.existsByEmail(email);
	}

	public List<Colaborador> findAll() {
		return colaboradorRepository.findAll();
	}

	public Optional<Colaborador> findById(UUID id) {
		return colaboradorRepository.findById(id);
	}

	@Transactional
	public void delete(Colaborador colaborador) {
		colaboradorRepository.delete(colaborador);
	}
}
