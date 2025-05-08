package co.edu.unisimon.integration.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unisimon.integration.model.*;
import co.edu.unisimon.integration.repository.EstudianteRepository;
import co.edu.unisimon.integration.repository.PerUsuarioRepository;

@Service
public class UserService {
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
    private PerUsuarioRepository perUsuarioRepository;
	
	@Autowired
	private ManagerUserGraph managerUserGraph;
	
	@Autowired
	private PowerShellService powerShellService;
	
	
	public Optional<Estudiante> buscarEstudiantePorIde(String estIde) {
        return estudianteRepository.findByEstIde(estIde);
    }
	
	public Optional<PerUsuario> buscarUsuarioPorUsuario(String usuUsuario) {
        return perUsuarioRepository.findByUsuUsuario(usuUsuario);
    }
	
	public ResponseEntity<Map<String, Object>> createUser(String username, String password) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			Optional<PerUsuario> perUsuarioOpt = buscarUsuarioPorUsuario(username);
			
			PerUsuario perUsuario = perUsuarioOpt.get();
			
			Optional<Estudiante> estudianteOpt = buscarEstudiantePorIde(perUsuario.getUsuIdentificacion());
			
			Estudiante estudiante = estudianteOpt.get();
			
			String email = username + "@unisimon.edu.co";
			String nombre = estudiante.getEstNombre1() + " " + estudiante.getEstNombre2();
			String apellido = estudiante.getEstApellido1() + " " + estudiante.getEstApellido2(); 
			String displayName = estudiante.getEstNombre1() + " " + estudiante.getEstNombre2() + " " + estudiante.getEstApellido1() + " " + estudiante.getEstApellido2();
			
			
			if (!managerUserGraph.userExists(email)) {
				managerUserGraph.createUser(email, username, nombre, apellido, displayName, password);
			} else {
				powerShellService.changePasswordAD(email, password);
			}
			
			response.put("success", true);
	        response.put("message", "Contraseña cambiada con éxito.");
	        return ResponseEntity.ok(response);
		
		} catch (Exception e) {
			response.put("success", false);
            response.put("message", "Faltan datos necesarios.");
            return ResponseEntity.badRequest().body(response);
		}
		
		
	}

}
