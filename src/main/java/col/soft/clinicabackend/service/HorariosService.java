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
    
    public HorariosResponse getHorarioById(Integer id) {
        return HorariosResponse.fromEntity(horariosRepository.findById(id).get());
    }
    
    public List<HorariosResponse> getHorariosDisponiblesByFecha(Date fecha, String estado) {
        return HorariosResponse.fromEntities(horariosRepository.findByDiaFechaAndEstado(fecha, estado));
    }

    public List<HorariosResponse> getHorariosByFecha(Date fecha) {
        return HorariosResponse.fromEntities(horariosRepository.findByDiaFecha(fecha));
    }

    public List<HorariosResponse> getHorariosByMedico(String name, String lastname) {
        return HorariosResponse.fromEntities(horariosRepository.findByMedicoNombresAndMedicoApellidos(name, lastname));
    }

    public List<HorariosResponse> getHorariosByFechaAndEspecialidad(Date fecha, String nameEspecialidad) {
        return HorariosResponse.fromEntities(horariosRepository.findByDiaFechaAndMedicoEspecialidadNombreEspecialidad(fecha, nameEspecialidad));   
    }
}
