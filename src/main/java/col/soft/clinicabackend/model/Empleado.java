
package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="empleado")
public class Empleado {
    @Id  
    @Column(name="dni",nullable=false)
    private Integer dni; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cargo",referencedColumnName="id_cargo" )
    private Cargo cargo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lugar_de_trabajo",referencedColumnName="id_lugar_de_trabajo" )
    private LugarDeTrabajo lugarDeTrabajo;

    @Column(name="nombres",nullable=false)
    private String nombres;
    
    @Column(name="apellidos",nullable=false)
    private String apellidos;
    
    @Column(name="fecha_de_nacimiento",nullable=false)
    private Date fechaDeNacimiento; 
    
    @Column(name="correo_electronico",nullable=false)
    private String correoElectronico;
    
    @Column(name="numero_celular",nullable=false)
    private Integer numeroCelular;
    
    @Column(name="domicilio",nullable=false)
    private String domicilio; 
}
