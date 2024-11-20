
package col.soft.clinicabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private Integer idUsuario;
    private Integer dni;
    private String idMedico;
    private String nombreUsuario;
    private String passwordUsuario;
}
