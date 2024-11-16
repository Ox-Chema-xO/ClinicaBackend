package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="horas")
public class Horas {
    @Id  
    @Column(name="id_horas",nullable=false)
    private Integer idHoras;    
    @Column(name="hora_inicio",nullable=false)
    private Time horaInicio;
    @Column(name="hora_fin",nullable=false)
    private Time horaFin;
    
}
