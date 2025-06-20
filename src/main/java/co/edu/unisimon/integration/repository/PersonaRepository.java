package co.edu.unisimon.integration.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unisimon.integration.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, String> {
	
	Optional<Persona> findByCorreoMicrosoft(String correoMicrosoft);

}
