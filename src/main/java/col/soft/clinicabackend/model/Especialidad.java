package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="especialidad")
public class Especialidad {
    @Id  
    @Column(name="id_especialidad",nullable=false)
    private String idEspecialidad;    
    @Column(name="nombre_especialidad",nullable=false)
    private String nombreEspecialidad;  
    @Column(name="salario",nullable=false)
    private BigDecimal salario;  
}
