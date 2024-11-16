package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="consultorio")
public class Consultorio {
    @Id  
    @Column(name="id_consultorio",nullable=false)
    private String idConsultorio;    
    @Column(name="nombre_consultorio",nullable=false)
    private String nombreConsultorio;       
}
