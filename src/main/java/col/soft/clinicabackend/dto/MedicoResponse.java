package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Consultorio;
import col.soft.clinicabackend.model.Especialidad;
import col.soft.clinicabackend.model.Medico;
import java.sql.Date;
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
public class MedicoResponse {
    private String idMedico; 
    private Consultorio consultorio;
    private Especialidad especialidad;
    private String nombres;    
    private String apellidos;      
    private Date fechaNacimiento;
    private Long numeroCelular;    
    private String correoElectronico;   
    private String direccion;   
    
    public static MedicoResponse fromEntity(Medico medico) {
    return MedicoResponse.builder()
            .idMedico(medico.getIdMedico())
            .consultorio(medico.getConsultorio())
            .especialidad(medico.getEspecialidad())
            .nombres(medico.getNombres())
            .apellidos(medico.getApellidos())
            .fechaNacimiento(medico.getFechaNacimiento())
            .numeroCelular(medico.getNumeroCelular())
            .correoElectronico(medico.getCorreoElectronico())
            .direccion(medico.getDireccion())
            .build();
    }
    
    public static List<MedicoResponse> fromEntities(List<Medico> medico){
        return medico.stream()
                .map(MedicoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
