package co.edu.unisimon.integration.controller;

import co.edu.unisimon.integration.model.aula.pregrado.PerUsuarioPregrado;
import co.edu.unisimon.integration.service.PerUsuarioPregradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pregrado/usuarios")
public class PerUsuarioPregradoController {

    
	private final PerUsuarioPregradoService usuarioService;

    @Autowired
    public PerUsuarioPregradoController(PerUsuarioPregradoService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{usuario}")
    public PerUsuarioPregrado obtenerPorUsuario(@PathVariable String usuario) {
        return usuarioService.buscarPorUsuario(usuario);
    }
    
}
