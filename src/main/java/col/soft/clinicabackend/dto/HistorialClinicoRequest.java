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
public class HistorialClinicoRequest {
    private String idHistorialClinico; 
    private Long dni; 
    private Date fecha; 
    private String observaciones; 
    private String diagnosticos;     
}
