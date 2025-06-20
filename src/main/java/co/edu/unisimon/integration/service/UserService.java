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
import co.edu.unisimon.integration.repository.PersonaRepository;

@Service
public class UserService {
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
    private PerUsuarioRepository perUsuarioRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
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
				System.out.println("email");
				managerUserGraph.changeUserPassword(email, password, false);
				//powerShellService.changePasswordAD(email, password);
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
	
	
	
public ResponseEntity<Map<String, Object>> createDocente(String username, String password, String documento) {
		
		Map<String, Object> response = new HashMap<>();
		
		String nombre1 = null;
		String nombre2 = null;
		String apellido1 = null;
		String apellido2 = null;
		
		try {
			
			Optional<Persona> personaOpt = null;
			Persona persona = null;
			String email = null;
			
			if(username == null) {
				System.out.println("Username " + documento);
				personaOpt = personaRepository.findById(documento);
				
				persona = personaOpt.get();
				
				nombre1 = persona.getNombre1() != null ? persona.getNombre1() : "";
				nombre2 = persona.getNombre2() != null ? persona.getNombre1() : "";
				apellido1 = persona.getApellido1() != null ? persona.getApellido1() : "";
				apellido2 = persona.getApellido2() != null ? persona.getApellido2() : "";
				
				email = persona.getCorreoMicrosoft();
				System.out.println("Aca vamos");
				System.out.println(nombre1);
				System.out.println("Aca vamos");
				System.out.println(apellido1);
				System.out.println(apellido2);
				if (email == null || email.contentEquals("")) {

					String letraApellido2 = apellido2.isEmpty() ? "" : apellido2.substring(0, 1);
					
					String letraNombre2 = nombre2.isEmpty() ? "" : nombre2.substring(0, 1);
					
					username = nombre1 + "." + apellido1 + letraApellido2;
					System.out.println("Prueba " + username);
					System.out.println("Aca vamos");
					email = username + "@unisimon.edu.co";
					email = email.toLowerCase();
					Optional<Persona> personaOptEmail = personaRepository.findByCorreoMicrosoft(email);
					
					System.out.println(username);
					if (personaOptEmail.isPresent()) {
						Persona personaEmail = personaOptEmail.get();
						if(personaEmail.getCedula() != persona.getCedula()) {
							username = nombre1 + letraNombre2 + "." + apellido1 + letraApellido2;
							email = username + "@unisimon.edu.co";
							email = email.toLowerCase();
						}
					}
					
					
					persona.setCorreoMicrosoft(email);
				} else {
					username = email.split("@")[0];
				}
				email = email.replaceAll(" ", "");
				System.out.println(email);
			}else {
				email = username + "@unisimon.edu.co";
				email = email.toLowerCase();
				System.out.println(email);
				personaOpt = personaRepository.findByCorreoMicrosoft(email);
				persona = personaOpt.get();
			}
			System.out.println("Aca vamos");
			personaRepository.save(persona);
			
			nombre1 = persona.getNombre1() != null ? persona.getNombre1() : "";
			nombre2 = persona.getNombre2() != null ? persona.getNombre2() : "";
			apellido1 = persona.getApellido1() != null ? persona.getApellido1() : "";
			apellido2 = persona.getApellido2() != null ? persona.getApellido2() : "";
			
			String nombre = nombre1 + " " + nombre2;
			String apellido = apellido1 + " " + apellido2; 
			String displayName = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
			System.out.println("3");
			System.out.println(displayName);
			System.out.println(email);
			
			
			if (!managerUserGraph.userExists(email)) {
				managerUserGraph.createUser(email, username, nombre, apellido, displayName, password);
			} else {
				managerUserGraph.changeUserPassword(email, password, false);
				//powerShellService.changePasswordAD(email, password);
			}
			
			response.put("success", true);
	        response.put("message", "Contraseña cambiada con éxito.");
	        return ResponseEntity.ok(response);
	        
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.put("success", false);
            response.put("message", "Faltan datos necesarios.");
            return ResponseEntity.badRequest().body(response);
		}
		
		
	}



	
	

}
