
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.dto.UsuarioResponse;
import col.soft.clinicabackend.repository.EmpleadoRepository;
import col.soft.clinicabackend.repository.MedicoRepository;
import col.soft.clinicabackend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    MedicoRepository medicoRepository;
    
    public List<UsuarioResponse> getUsuarios(){
        return UsuarioResponse.fromEntities(usuarioRepository.findAll());
    }
    
    public UsuarioResponse getUsuarioByNombreAndPassword(String nombreUsuario, String passwordUsuario){
        return UsuarioResponse.fromEntity(usuarioRepository.findByNombreUsuarioAndPasswordUsuario(nombreUsuario, passwordUsuario));
    }
}
