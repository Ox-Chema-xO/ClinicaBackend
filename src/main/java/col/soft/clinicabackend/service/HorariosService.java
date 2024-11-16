package col.soft.clinicabackend.service;
import col.soft.clinicabackend.dto.HorariosResponse;
import col.soft.clinicabackend.repository.HorariosRepository;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorariosService {
    @Autowired
    HorariosRepository horariosRepository;
    
    public List<HorariosResponse> getHorarios() {
        return HorariosResponse.fromEntities(horariosRepository.findAll());
    }

    public List<HorariosResponse> getHorariosDisponiblesByFecha(Date fecha, String estado) {
        return HorariosResponse.fromEntities(horariosRepository.findByDiaFechaAndEstado(fecha, estado));
        
    }


}
