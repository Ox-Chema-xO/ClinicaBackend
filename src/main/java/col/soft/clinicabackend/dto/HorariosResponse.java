package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Horarios;
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
public class HorariosResponse {
    private Integer idHorarios;   
    private String nombresMedico;
    private String apellidosMedico; 
    private String nombreEspecialidad;
    private Date fecha;             
    private Time horaInicio;        
    private Time horaFin;               
    private String estado;            

    public static HorariosResponse fromEntity(Horarios horarios) {
        return HorariosResponse.builder()
                .idHorarios(horarios.getIdHorarios())
                .nombresMedico(horarios.getMedico().getNombres())
                .apellidosMedico(horarios.getMedico().getApellidos())
                .nombreEspecialidad(horarios.getMedico().getEspecialidad().getNombreEspecialidad())
                .fecha(horarios.getDia().getFecha())
                .horaInicio(horarios.getHoras().getHoraInicio())
                .horaFin(horarios.getHoras().getHoraFin())
                .estado(horarios.getEstado())
                .build();
    }

    public static List<HorariosResponse> fromEntities(List<Horarios> horariosList) {
        return horariosList.stream()
                .map(HorariosResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
