package com.api.pkg.controllers;

import java.util.List;
import java.util.Optional;

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

import com.api.pkg.dtos.DepartamentoDto;
import com.api.pkg.models.Departamento;
import com.api.pkg.services.DepartamentoService;

@RestController
@RequestMapping(value = "/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;

	@PostMapping
	public ResponseEntity<Object> saveDepartamento(@RequestBody @Valid DepartamentoDto departamentoDto){
		
		if (departamentoService.existsByNomeDepart(departamentoDto.getNomeDepart())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O campo Nome do Departamento já estão em uso!");
		}
		if (departamentoService.existsByRamal(departamentoDto.getRamal())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Esse O campo Ramal já estão em uso!");
		}
		Departamento departamento = new Departamento();
		BeanUtils.copyProperties(departamentoDto, departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.save(departamento));
	}

	@GetMapping
	public ResponseEntity<List<Departamento>> getALLDeparamento(){
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneDepartamento(@PathVariable(value = "id") Long id){
		Optional<Departamento> departamentoOptional= departamentoService.findById(id);
		if (!departamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não foi localizado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(departamentoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartamento(@PathVariable(value = "id") Long id){
		Optional<Departamento> departamentoOptional= departamentoService.findById(id);
		if (!departamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não foi localizado!");
		}
		departamentoService.delete(departamentoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(departamentoOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable(value = "id") Long id, 
													 @RequestBody @Valid DepartamentoDto departamentoDto){
		Optional<Departamento> departamentoOptional= departamentoService.findById(id);
		if (!departamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não foi localizado!");
		}
		Departamento departamento = new Departamento();
		BeanUtils.copyProperties(departamentoDto, departamento);
		departamento.setId(departamentoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.save(departamento));
	}
}