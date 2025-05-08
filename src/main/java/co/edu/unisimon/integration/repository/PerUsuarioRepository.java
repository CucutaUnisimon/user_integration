package co.edu.unisimon.integration.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unisimon.integration.model.PerUsuario;

public interface PerUsuarioRepository extends CrudRepository<PerUsuario, String> {
	
	Optional<PerUsuario> findByUsuUsuario(String usuUsuario);

}
