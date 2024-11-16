package col.soft.clinicabackend.dto;

import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitaRequest {
    private String idCita;          
    private Long dni;     
    private Integer idHorarios;      
    private Date fecha;             
    private Time horaInicio;       
    private Time horaFin;           
    private String estado;        
}
