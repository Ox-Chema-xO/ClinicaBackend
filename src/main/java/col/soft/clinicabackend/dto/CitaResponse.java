package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Cita;
import java.sql.Date;
import java.sql.Time;
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
public class CitaResponse {
    private String idCita;
    private Long dniPaciente;
    private String nombresPaciente;
    private String apellidosPaciente;
    private String nombresMedico;
    private String apellidosMedico;    
    private Date fecha;             
    private Time horaInicio;        
    private Time horaFin;           
    private String estado;          

    public static CitaResponse fromEntity(Cita cita) {
        return CitaResponse.builder()
                .idCita(cita.getIdCita())
                .dniPaciente(cita.getPaciente().getDni())
                .nombresPaciente(cita.getPaciente().getNombres())
                .apellidosPaciente(cita.getPaciente().getApellidos())
                .nombresMedico(cita.getHorario().getMedico().getNombres())
                .apellidosMedico(cita.getHorario().getMedico().getApellidos())      
                .fecha(cita.getFecha())
                .horaInicio(cita.getHoraInicio())
                .horaFin(cita.getHoraFin())
                .estado(cita.getEstado())
                .build();
    }

    public static List<CitaResponse> fromEntities(List<Cita> citas) {
        return citas.stream()
                .map(CitaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

