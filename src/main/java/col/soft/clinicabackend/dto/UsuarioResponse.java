
package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Empleado;
import col.soft.clinicabackend.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Integer idUsuario;
    private Integer dni;
    private String idMedico;
    
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .dni(usuario.getEmpleado()==null ? null : usuario.getEmpleado().getDni())
                .idMedico(usuario.getMedico()==null ? "" : usuario.getMedico().getIdMedico())
                .build();
    }

    public static List<UsuarioResponse> fromEntities(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
