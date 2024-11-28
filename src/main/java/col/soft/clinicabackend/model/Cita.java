package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Time;
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
@Table(name="cita")
public class Cita {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    private String idCita; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dni",referencedColumnName="dni" )
    private Paciente paciente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horarios", referencedColumnName="id_horarios")
    private Horarios horario;
    
    @Column(name="fecha",nullable=false)
    private Date fecha; 
    
    @Column(name="hora_inicio",nullable=false)
    private Time horaInicio; 
    
    @Column(name="hora_fin",nullable=false)
    private Time horaFin; 
    
    @Column(name="estado",nullable=false)
    private String estado; 
    
}
