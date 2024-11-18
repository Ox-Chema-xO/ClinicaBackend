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
    
    public Paciente insertPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
        
    public Paciente updatePacienteByDni(Long dni,Paciente newpaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(dni);
        if (!optionalPaciente.isPresent()) {  
           return null; 
        }
        Paciente paciente = optionalPaciente.get();
        paciente.setNombres(newpaciente.getNombres());
        paciente.setApellidos(newpaciente.getApellidos());
        paciente.setFechaDeNacimiento(newpaciente.getFechaDeNacimiento());
        paciente.setCorreoElectronico(newpaciente.getCorreoElectronico());
        paciente.setNumeroCelular(newpaciente.getNumeroCelular());
        paciente.setDomicilio(newpaciente.getDomicilio());
        return pacienteRepository.save(paciente);
       
    }

} 
    
    
