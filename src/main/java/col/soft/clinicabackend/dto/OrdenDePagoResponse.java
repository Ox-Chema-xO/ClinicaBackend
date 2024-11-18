
package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Cita;
import col.soft.clinicabackend.model.ComprobanteDePago;
import col.soft.clinicabackend.model.MetodoDePago;
import col.soft.clinicabackend.model.OrdenDePago;
import java.math.BigDecimal;
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
public class OrdenDePagoResponse {
    private String idOrdenDePago;
    private String idCita;
    private Date fecha;
    private String nombresPaciente;
    private String apellidosPaciente;
    private Long dniPaciente;
    private String comprobanteDePago;
    private String metodoDePago;
    private BigDecimal monto;
    private String estado;
    
    public static OrdenDePagoResponse fromEntity(OrdenDePago ordenDePago){
        return OrdenDePagoResponse.builder()
                .idOrdenDePago(ordenDePago.getIdOrdenDePago()) //idOrdenDePago
                .idCita(ordenDePago.getCita().getIdCita()) //idcita
                .fecha(ordenDePago.getFecha()) //fecha
                .nombresPaciente(ordenDePago.getCita().getPaciente().getNombres()) //nombresPaciente
                .apellidosPaciente(ordenDePago.getCita().getPaciente().getApellidos()) //apellidosPaciente
                .dniPaciente(ordenDePago.getCita().getPaciente().getDni()) //dniPaciente
                .comprobanteDePago(ordenDePago.getComprobanteDePago()==null ? "" : ordenDePago.getComprobanteDePago().getTipo()) //comprobanteDePago
                .metodoDePago(ordenDePago.getMetodoDePago()==null ? "" : ordenDePago.getMetodoDePago().getTipo()) //metodoDePago
                .monto(ordenDePago.getMonto()) //monto
                .estado(ordenDePago.getEstado()) //estado
                .build();
    }
    
    public static List<OrdenDePagoResponse> fromEntities(List<OrdenDePago> ordenesDePago){
        return ordenesDePago.stream()
                .map(OrdenDePagoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
    
