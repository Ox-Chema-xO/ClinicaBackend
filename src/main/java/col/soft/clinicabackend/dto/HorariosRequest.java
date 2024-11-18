package col.soft.clinicabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorariosRequest {
    private Integer idHorarios;        
    private String idMedico;          
    private Integer idDia;            
    private Integer idHoras;          
    private String estado;            
}