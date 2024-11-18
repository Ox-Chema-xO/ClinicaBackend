package col.soft.clinicabackend.service;

import col.soft.clinicabackend.dto.MedicoRequest;
import col.soft.clinicabackend.dto.MedicoResponse;
import col.soft.clinicabackend.model.Consultorio;
import col.soft.clinicabackend.model.Especialidad;
import col.soft.clinicabackend.model.Medico;
import col.soft.clinicabackend.repository.ConsultorioRepository;
import col.soft.clinicabackend.repository.EspecialidadRepository;
import col.soft.clinicabackend.repository.MedicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;
    @Autowired
    ConsultorioRepository consultorioRepository;
    
    public List<MedicoResponse> getMedicos() {
        return MedicoResponse.fromEntities(medicoRepository.findAll());
    }

    public MedicoResponse findMedicoById(String id) {
        return MedicoResponse.fromEntity(medicoRepository.findById(id).get());
    }
    public MedicoResponse insertMedico(MedicoRequest medicoRequest) {
        //verificar que consultorio y especialidad existan en la bd
        String idConsultorio = medicoRequest.getIdConsultorio();
        Consultorio consultorio = consultorioRepository.findById(idConsultorio).get();
        if(consultorio==null) return new MedicoResponse();
        
        String idEspecialidad = medicoRequest.getIdEspecialidad();
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad).get();
        if(especialidad==null) return new MedicoResponse();
        
        Medico medico = new Medico(
                medicoRequest.getIdMedico(),
                consultorio,
                especialidad,
                medicoRequest.getNombres(),
                medicoRequest.getApellidos(),
                medicoRequest.getFechaNacimiento(),
                medicoRequest.getNumeroCelular(),
                medicoRequest.getCorreoElectronico(),
                medicoRequest.getDireccion()
        );
        medico = medicoRepository.save(medico);
        MedicoResponse medicoResponse = MedicoResponse.fromEntity(medico);
        return medicoResponse;
    }

    public MedicoResponse updateMedico(String id, MedicoRequest medicoRequest) {
        Medico medico = medicoRepository.findById(id).get();
        if(medico == null) return new MedicoResponse();
        medico.setNombres(medicoRequest.getNombres());
        medico.setApellidos(medicoRequest.getApellidos());
        medico.setCorreoElectronico(medicoRequest.getCorreoElectronico());
        medico.setDireccion(medicoRequest.getDireccion());
        medico.setNumeroCelular(medicoRequest.getNumeroCelular());
        medico.setFechaNacimiento(medicoRequest.getFechaNacimiento());
        
        Consultorio consultorio = consultorioRepository.findById(medicoRequest.getIdConsultorio()).get();
        if(consultorio==null) return new MedicoResponse();
        medico.setConsultorio(consultorio);
        
        Especialidad especialidad = especialidadRepository.findById(medicoRequest.getIdEspecialidad()).get();
        if(especialidad==null) return new MedicoResponse();
        medico.setEspecialidad(especialidad);
        
        medico = medicoRepository.save(medico);
        MedicoResponse medicoResponse = MedicoResponse.fromEntity(medico);
        return medicoResponse;
        
        
    }
    
    public List<MedicoResponse> findByEspecialidadNombre(String nameEspecialidad) {
        return MedicoResponse.fromEntities(medicoRepository.findByEspecialidadNombreEspecialidad(nameEspecialidad));
    }
    
    public List<MedicoResponse> findByConsultorioNombre(String nameConsultorio) {
        return MedicoResponse.fromEntities(medicoRepository.findByConsultorioNombreConsultorio(nameConsultorio));
    }
 
    public void deleteMedicoById(String id) {
         medicoRepository.deleteById(id);
    }
    
}
