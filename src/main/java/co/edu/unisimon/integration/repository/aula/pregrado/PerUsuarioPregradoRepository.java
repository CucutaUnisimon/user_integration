package co.edu.unisimon.integration.repository.aula.pregrado;

import co.edu.unisimon.integration.model.aula.pregrado.PerUsuarioPregrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerUsuarioPregradoRepository extends JpaRepository<PerUsuarioPregrado, String> {

    // Puedes agregar consultas personalizadas aquí si las necesitas
    PerUsuarioPregrado findByUsuario(String usuario);
    
    PerUsuarioPregrado findByCorreo(String correo);

    PerUsuarioPregrado findByIdentificacion(String identificacion);
}

