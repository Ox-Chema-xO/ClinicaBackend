package col.soft.clinicabackend.service;


import col.soft.clinicabackend.dto.HistorialClinicoRequest;
import col.soft.clinicabackend.dto.HistorialClinicoResponse;
import col.soft.clinicabackend.model.HistorialClinico;
import col.soft.clinicabackend.model.Paciente;
import col.soft.clinicabackend.repository.HistorialClinicoRepository;
import col.soft.clinicabackend.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialClinicoService {
    @Autowired
    HistorialClinicoRepository historialClinicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    
    public List<HistorialClinicoResponse> getHistorialesClinicos(){
        return HistorialClinicoResponse.fromEntities(historialClinicoRepository.findAll());
    }
    
    public HistorialClinicoResponse findHistorialClinicoById(String id){
        return HistorialClinicoResponse.fromEntity(historialClinicoRepository.findById(id).get()); 
    }
    
    public HistorialClinicoResponse findHistorialClinicoByPacienteDni(Long dni){            
        return HistorialClinicoResponse.fromEntity(historialClinicoRepository.findByPacienteDni(dni)); 
    }
    public HistorialClinicoResponse insertHistorialClinico(HistorialClinicoRequest historialClinicoRequest) {
        //verificar que el paciente este en la bd
        Long dniPaciente = historialClinicoRequest.getDni();
        Paciente paciente = pacienteRepository.findById(dniPaciente).get();
        if(paciente==null) return new HistorialClinicoResponse();
        
        HistorialClinico historialClinico = new HistorialClinico(
                historialClinicoRequest.getIdHistorialClinico(),
                paciente,
                historialClinicoRequest.getFecha(),
                historialClinicoRequest.getObservaciones(),
                historialClinicoRequest.getDiagnosticos()
        );
        
        historialClinico = historialClinicoRepository.save(historialClinico);
        HistorialClinicoResponse historialClinicoResponse = HistorialClinicoResponse.fromEntity(historialClinico);
        return historialClinicoResponse;
    }
    
    public HistorialClinicoResponse updateHistorialClinico(Long dni, HistorialClinicoRequest historialClinicoRequest) {
        HistorialClinico historialClinico = historialClinicoRepository.findByPacienteDni(dni);
        if(historialClinico==null) return new HistorialClinicoResponse();
        
        historialClinico.setObservaciones(historialClinicoRequest.getObservaciones());
        historialClinico.setDiagnosticos(historialClinicoRequest.getDiagnosticos());
        
        historialClinico = historialClinicoRepository.save(historialClinico);
        HistorialClinicoResponse historialClinicoResponse = HistorialClinicoResponse.fromEntity(historialClinico);
        return historialClinicoResponse;
    }

}
