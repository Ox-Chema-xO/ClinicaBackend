package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{
    
}