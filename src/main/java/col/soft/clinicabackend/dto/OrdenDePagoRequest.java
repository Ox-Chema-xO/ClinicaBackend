
package col.soft.clinicabackend.dto;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDePagoRequest {
    private String idOrdenDePago;
    private String idCita;
    private Integer idComprobanteDePago;
    private Integer idMetodoDePago;
    private Date fecha;
    private BigDecimal monto;
    private String estado;
}
