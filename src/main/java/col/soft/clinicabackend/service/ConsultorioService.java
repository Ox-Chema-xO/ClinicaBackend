package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.Consultorio;
import col.soft.clinicabackend.repository.ConsultorioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultorioService {
    @Autowired
    ConsultorioRepository consultorioRepository;

    public List<Consultorio> getConsultorios() {
        return consultorioRepository.findAll();
    }
    
}
