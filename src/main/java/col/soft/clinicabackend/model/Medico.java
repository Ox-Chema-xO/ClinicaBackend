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
@Table(name="medico")
public class Medico {
    @Id  
    @Column(name="id_medico",nullable=false)
    private String idMedico; 
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consultorio",referencedColumnName="id_consultorio")
    private Consultorio consultorio;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_especialidad", referencedColumnName="id_especialidad")
    private Especialidad especialidad;
    
    @Column(name="nombres",nullable=false)
    private String nombres;    
    @Column(name="apellidos",nullable=false)
    private String apellidos;      
    @Column(name="fecha_nacimiento",nullable=false)
    private Date fechaNacimiento;
    @Column(name="numero_celular",nullable=false)
    private Long numeroCelular;      
    @Column(name="correo_electronico")
    private String correoElectronico;   
    @Column(name="direccion",nullable=false)
    private String direccion;   
  
}
