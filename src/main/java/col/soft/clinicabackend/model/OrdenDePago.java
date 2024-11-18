
package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ordendepago")
public class OrdenDePago {
    @Id  
    @Column(name="id_orden_de_pago",nullable=false)
    private String idOrdenDePago; 
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cita",referencedColumnName="id_cita")
    private Cita cita;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comprobante_de_pago",referencedColumnName="id_comprobante_de_pago")
    private ComprobanteDePago comprobanteDePago; 
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_metodo_de_pago",referencedColumnName="id_metodo_de_pago")
    private MetodoDePago metodoDePago; 
    
    @Column(name="fecha",nullable=false)
    private Date fecha; 
    
    @Column(name="monto",nullable=false)
    private BigDecimal monto;
    
    @Column(name="estado",nullable=false)
    private String estado;
}
