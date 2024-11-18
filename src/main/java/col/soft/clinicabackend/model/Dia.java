
package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="dia")
public class Dia {
    @Id  
    @Column(name="id_dia",nullable=false)
    private Integer idDia;    
    @Column(name="fecha",nullable=false)
    private Date fecha;  
    
    
}
