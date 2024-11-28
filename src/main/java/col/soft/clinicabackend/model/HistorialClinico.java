package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name="historialclinico")
public class HistorialClinico {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_historial_clinico")
    private String idHistorialClinico; 
    
    @OneToOne(fetch = FetchType.EAGER)//Lazy
    @JoinColumn(name="dni", referencedColumnName="dni")
    private Paciente paciente; 
    
    @Column(name="fecha")
    private Date fecha; 
    
    @Column(name="observaciones")
    private String observaciones; 
    
    @Column(name="diagnosticos")
    private String diagnosticos;     
    
}
