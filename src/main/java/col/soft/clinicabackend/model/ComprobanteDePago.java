
package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comprobantedepago")
public class ComprobanteDePago {
    @Id  
    @Column(name="id_comprobante_de_pago",nullable=false)
    private Integer idComprobanteDePago;    
    
    @Column(name="tipo",nullable=false)
    private String tipo;  
}
