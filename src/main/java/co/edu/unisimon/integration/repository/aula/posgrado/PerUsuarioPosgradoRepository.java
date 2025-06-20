package co.edu.unisimon.integration.repository.aula.posgrado;

import co.edu.unisimon.integration.model.aula.posgrado.PerUsuarioPosgrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerUsuarioPosgradoRepository extends JpaRepository<PerUsuarioPosgrado, String> {

    // Puedes agregar consultas personalizadas aquí si las necesitas
	PerUsuarioPosgrado findByUsuario(String usuario);
    
	PerUsuarioPosgrado findByCorreo(String correo);

	PerUsuarioPosgrado findByIdentificacion(String identificacion);
}

