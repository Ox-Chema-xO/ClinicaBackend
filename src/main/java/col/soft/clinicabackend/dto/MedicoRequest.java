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
public class MedicoRequest {
    private String idMedico; 
    private String idConsultorio;
    private String idEspecialidad;
    private String nombres;    
    private String apellidos;      
    private Date fechaNacimiento;
    private Long numeroCelular;    
    private String correoElectronico;   
    private String direccion;   
}
