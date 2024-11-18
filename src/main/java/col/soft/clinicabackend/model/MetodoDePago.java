
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
@Table(name="metododepago")
public class MetodoDePago {
    @Id  
    @Column(name="id_metodo_de_pago",nullable=false)
    private Integer idMetodoDePago;   
    
    @Column(name="tipo",nullable=false)
    private String tipo;  
}
