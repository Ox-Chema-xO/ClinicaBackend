package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="paciente")
public class Paciente {
    /*
    public enum Email {
    }*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="dni")
    private Long Dni;    
    @Column(name="nombres")
    private String Nombres;    
    @Column(name="apellidos")
    private String Apellidos;      
    @Column(name="fecha_de_nacimiento")
    private Date FechaDeNacimiento;  
    @Column(name="correo_electronico")
    private String CorreoElectronico;
    //private Email CorreoElectronico;    
    @Column(name="numero_celular")
    private Long NumeroCelular;    
    @Column(name="domicilio")
    private String Domicilio;    
}