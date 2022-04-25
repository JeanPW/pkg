package com.api.pkg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pkg.models.Departamento;
import com.api.pkg.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	public Departamento save(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}

	public boolean existsByNomeDepart(String nomeDepart) {
		return departamentoRepository.existsByNomeDepart(nomeDepart);
	}

	public boolean existsByRamal(String ramal) {
		return departamentoRepository.existsByRamal(ramal);
	}

	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

	public Optional<Departamento> findById(Long id) {
		return departamentoRepository.findById(id);
	}

	public void delete(Departamento departamento) {
		departamentoRepository.delete(departamento);
	}

}
