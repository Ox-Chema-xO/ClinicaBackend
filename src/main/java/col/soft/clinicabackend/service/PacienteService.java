package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.Paciente;
import col.soft.clinicabackend.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;
    
    public List<Paciente> getPacientes(){
        return pacienteRepository.findAll();
    }
    
    public Optional<Paciente> findPacienteByDni(Long dni){
        return pacienteRepository.findById(dni);
    }
}