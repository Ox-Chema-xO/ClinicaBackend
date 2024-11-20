
package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dni",referencedColumnName="dni" )
    private Empleado empleado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico",referencedColumnName="id_medico" )
    private Medico medico;
    
    @Column(name="nombre_usuario",nullable=false)
    private String nombreUsuario;
    
    @Column(name="password_usuario",nullable=false)
    private String passwordUsuario;
}
