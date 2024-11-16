package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="paciente")
public class Paciente {
    @Id  
    @Column(name="dni",nullable=false)
    private Long dni;    
    @Column(name="nombres",nullable=false)
    private String nombres;    
    @Column(name="apellidos",nullable=false)
    private String apellidos;      
    @Column(name="fecha_de_nacimiento",nullable=false)
    private Date fechaDeNacimiento;  
    @Column(name="correo_electronico")
    private String correoElectronico;   
    @Column(name="numero_celular",nullable=false)
    private Long numeroCelular;    
    @Column(name="domicilio",nullable=false)
    private String domicilio;    
}