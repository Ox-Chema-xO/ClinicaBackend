package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="historialclinico")
public class HistorialClinico {
    
    @Id  
    @Column(name="id_historial_clinico",nullable=false)
    private String idHistorialClinico; 
    
    @OneToOne(fetch = FetchType.EAGER)//Lazy
    @JoinColumn(name="dni", referencedColumnName="dni")
    private Paciente paciente; 
    
    @Column(name="fecha",nullable=false)
    private Date fecha; 
    
    @Column(name="observaciones")
    private String observaciones; 
    
    @Column(name="diagnosticos")
    private String diagnosticos;     
    
}
