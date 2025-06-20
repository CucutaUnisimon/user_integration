package co.edu.unisimon.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HealthController {
	
	private static final Logger logger = LoggerFactory.getLogger(HealthController.class);
	

	
	@GetMapping("/healthcheck")
	public String healthCheck() {
		// Ok del servidor
		logger.debug("Mensaje de debug con detalles técnicos");
        logger.info("Operación completada con éxito");
        logger.warn("Advertencia: recurso casi agotado");
		return "Ok";
	}
	


}
