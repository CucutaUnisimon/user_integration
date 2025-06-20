package co.edu.unisimon.integration.service;

import co.edu.unisimon.integration.model.aula.pregrado.PerUsuarioPregrado;
import co.edu.unisimon.integration.repository.aula.pregrado.PerUsuarioPregradoRepository;
import co.edu.unisimon.integration.utils.PasswordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerUsuarioPregradoService {

	
    private final PerUsuarioPregradoRepository usuarioRepository;
    
    @Autowired 
    PasswordService passwordService;

    @Autowired
    public PerUsuarioPregradoService(PerUsuarioPregradoRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public PerUsuarioPregrado buscarPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }
    
    
    public boolean cambiarClave(String usuario, String clave) {
    	
    	PerUsuarioPregrado usuarioAula = buscarPorUsuario(usuario);
    	
    	String md5Clave = null;
    	String base10Clave = null;
		try {
			md5Clave = passwordService.md5(clave);
			base10Clave = passwordService.base10(clave);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

    	usuarioAula.setPassword(md5Clave);
    	usuarioRepository.save(usuarioAula);
    	return true;
    }
    
    
}
