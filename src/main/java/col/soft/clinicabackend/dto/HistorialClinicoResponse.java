package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.HistorialClinico;
import col.soft.clinicabackend.model.Paciente;
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
public class HistorialClinicoResponse {
    private String idHistorialClinico; 
    private Paciente paciente; 
    private Date fecha; 
    private String observaciones; 
    private String diagnosticos;  
    
    
    public static HistorialClinicoResponse fromEntity(HistorialClinico historialClinico){
        return HistorialClinicoResponse.builder()
                .idHistorialClinico(historialClinico.getIdHistorialClinico())
                .paciente(historialClinico.getPaciente()) 
                .fecha(historialClinico.getFecha())
                .observaciones(historialClinico.getObservaciones())
                .diagnosticos(historialClinico.getDiagnosticos())
                .build();
    }
    
    public static List<HistorialClinicoResponse> fromEntities(List<HistorialClinico> historialClinico){
        return historialClinico.stream()
                .map(HistorialClinicoResponse::fromEntity)
                .collect(Collectors.toList());
    }
        
}
