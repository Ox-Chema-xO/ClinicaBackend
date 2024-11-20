
package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    public Usuario findByNombreUsuarioAndPasswordUsuario(String nombreUsuario, String passwordUsuario);
}
