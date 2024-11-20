
package col.soft.clinicabackend.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private Integer dni;
    private Integer idCargo;
    private Integer idLugarDeTrabajo;
    private String nombres;
    private String apellidos;
    private Date fechaDeNacimiento;
    private String correoElectronico;
    private Integer numeroCelular;
    private String domicilio;
}
