package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico,String> {
    HistorialClinico findByPacienteDni(Long dni);

}
