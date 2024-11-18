package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.Cita;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita,String>{
    List<Cita> findByFechaAndEstado(Date fecha, String estado);
}

