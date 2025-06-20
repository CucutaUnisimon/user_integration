package co.edu.unisimon.integration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unisimon.integration.dto.UserRequest;
import co.edu.unisimon.integration.service.ManagerUserGraph;
import co.edu.unisimon.integration.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/change_pass")
    public ResponseEntity<Map<String, Object>> checkUser(@RequestBody UserRequest request) {
        
		return userService.createUser(request.getUsername(), request.getPassword());
		
    }
	
	
	@PostMapping("/change_pass_docente")
    public ResponseEntity<Map<String, Object>> checkUserDocente(@RequestBody UserRequest request) {
		
        
		String username = request.getUsername();
		String password = request.getPassword();
		String documento = request.getDocumento();
		
		System.out.println(username);
		System.out.println(documento);
		
		
		return userService.createDocente(username, password, documento);
		
    }
	
}
