package co.edu.unisimon.integration.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unisimon.integration.model.Estudiante;

public interface EstudianteRepository extends CrudRepository<Estudiante, BigDecimal> {
	
	Optional<Estudiante> findByEstIde(String estIde);

}
