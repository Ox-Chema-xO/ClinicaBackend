package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.Horarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorariosRepository extends JpaRepository<Horarios,Integer> {
    List<Horarios> findByDiaFechaAndEstado(Date fecha, String estado);
}
