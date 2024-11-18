package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.Medico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,String>{
    List<Medico> findByEspecialidadNombreEspecialidad(String nombreE);
    List<Medico> findByConsultorioNombreConsultorio(String nombreC);
}
