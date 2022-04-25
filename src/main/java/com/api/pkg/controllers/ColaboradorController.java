package com.api.pkg.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pkg.dtos.ColaboradorDto;
import com.api.pkg.models.Colaborador;
import com.api.pkg.services.ColaboradorService;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService colaboradorService;

	@PostMapping
	public ResponseEntity<Object> saveColaborador(@RequestBody @Valid ColaboradorDto colaboradorDto){
		
		if (colaboradorService.existsByNome(colaboradorDto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O campo Nome já estão em uso!");
		}
		if (colaboradorService.existsByCpf(colaboradorDto.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O campo CPF já estão em uso!");
		}
		if (colaboradorService.existsByEmail(colaboradorDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O campo Email já estão em uso!");
		}	
		Colaborador colaborador = new Colaborador();
		BeanUtils.copyProperties(colaboradorDto, colaborador);
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.save(colaborador));
	}

	@GetMapping
	public ResponseEntity<List<Colaborador>> getALLColaborador(){
		return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneColaborador(@PathVariable(value = "id") UUID id){
		Optional<Colaborador> colaboradorOptional= colaboradorService.findById(id);
		if (!colaboradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não localizado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(colaboradorOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> DeleteColaborador(@PathVariable(value = "id") UUID id){
		Optional<Colaborador> colaboradorOptional= colaboradorService.findById(id);
		if (!colaboradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não localizado!");
		}
		colaboradorService.delete(colaboradorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(colaboradorOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> deleteColaborador(@PathVariable(value = "id") UUID id,
													@RequestBody @Valid ColaboradorDto colaboradorDto){
		Optional<Colaborador> colaboradorOptional= colaboradorService.findById(id);
		if (!colaboradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não localizado!");
		}	
		Colaborador colaborador = new Colaborador();
		BeanUtils.copyProperties(colaboradorDto, colaborador);
		colaborador.setId(colaboradorOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.save(colaborador));
	}
}