package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Dia;
import col.soft.clinicabackend.model.Horarios;
import col.soft.clinicabackend.model.Horas;
import col.soft.clinicabackend.model.Medico;
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
    private String idMedico;
    private String nombresMedico;
    private String apellidosMedico;           
    private Dia dia;            
    private Horas horas;          
    private String estado;            

    public static HorariosResponse fromEntity(Horarios horarios) {
        return HorariosResponse.builder()
                .idHorarios(horarios.getIdHorarios())
                .idMedico(horarios.getMedico().getIdMedico())
                .nombresMedico(horarios.getMedico().getNombres())
                .apellidosMedico(horarios.getMedico().getApellidos())
                .dia(horarios.getDia())
                .horas(horarios.getHoras())
                .estado(horarios.getEstado())
                .build();
    }

    public static List<HorariosResponse> fromEntities(List<Horarios> horariosList) {
        return horariosList.stream()
                .map(HorariosResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
