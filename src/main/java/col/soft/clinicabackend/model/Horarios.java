package col.soft.clinicabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "horarios")
public class Horarios {

    @Id
    @Column(name = "id_horarios", nullable = false)
    private Integer idHorarios;

    @OneToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_dia", referencedColumnName = "id_dia", nullable = false)
    private Dia dia;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_horas", referencedColumnName = "id_horas", nullable = false)
    private Horas horas;

    @Column(name = "estado", nullable = false)
    private String estado;
}
