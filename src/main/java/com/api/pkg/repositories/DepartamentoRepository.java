package com.api.pkg.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.pkg.models.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	boolean existsByNomeDepart(String nomeDepart);
	
	boolean existsByOrcamento(double orcamento);
	
	boolean existsByRamal(String ramal);
}
