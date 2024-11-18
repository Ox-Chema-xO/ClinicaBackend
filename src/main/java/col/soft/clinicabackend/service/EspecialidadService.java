package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.Especialidad;
import col.soft.clinicabackend.repository.EspecialidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService {
    @Autowired 
    EspecialidadRepository especialidadRepository;
    
    public List<Especialidad> getEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> findEspecialidadById(String idEspecialidad) {
        return especialidadRepository.findById(idEspecialidad);
    }
        
    public Especialidad insertEspecialidad(Especialidad especialidad) {
        return  especialidadRepository.save(especialidad);
 
    }

    public void deleteEspecialidad(String id) {
        especialidadRepository.deleteById(id);
        
    }
    
        
    
}
