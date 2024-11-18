package col.soft.clinicabackend.service;

import col.soft.clinicabackend.dto.CitaRequest;
import col.soft.clinicabackend.dto.CitaResponse;
import col.soft.clinicabackend.model.Cita;
import col.soft.clinicabackend.model.Horarios;
import col.soft.clinicabackend.model.Paciente;
import col.soft.clinicabackend.repository.CitaRepository;
import col.soft.clinicabackend.repository.HorariosRepository;
import col.soft.clinicabackend.repository.PacienteRepository;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {
    @Autowired
    CitaRepository citaRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public List<CitaResponse> getCitas() {
        return CitaResponse.fromEntities(citaRepository.findAll());
    }
    public CitaResponse getCitaById(String id) {
        return CitaResponse.fromEntity(citaRepository.findById(id).get());
    }

    public List<CitaResponse> getCitasEstadosByFecha(Date fecha, String estado) {
        return CitaResponse.fromEntities(citaRepository.findByFechaAndEstado(fecha, estado));
    }
    
    public CitaResponse insertCita(CitaRequest citaRequest) {
        //verificar que paciente y horario existan en la bd
        Long dni = citaRequest.getDni();
        Paciente paciente = pacienteRepository.findById(dni).get();
        if(paciente==null) return new CitaResponse();
        
        Integer idHorario = citaRequest.getIdHorarios();
        Horarios horario = horariosRepository.findById(idHorario).get();
        if(horario==null) return new CitaResponse();
        horario.setEstado("reservado");
        
        Cita cita = new Cita(
                citaRequest.getIdCita(),
                paciente,
                horario,
                horario.getDia().getFecha(),
                horario.getHoras().getHoraInicio(),
                horario.getHoras().getHoraFin(),
                "pendiente"
        );
        cita = citaRepository.save(cita);
        CitaResponse citaResponse = CitaResponse.fromEntity(cita);
        return citaResponse;       
    }

    public CitaResponse updateCita(String id, CitaRequest citaRequest) {
        Cita cita = citaRepository.findById(id).get();
        if(cita == null) return new CitaResponse();
        Horarios anteriorHorario = horariosRepository.findById(cita.getHorario().getIdHorarios()).get();
        anteriorHorario.setEstado("libre");
        
        Horarios newhorario = horariosRepository.findById(citaRequest.getIdHorarios()).get();
        if(newhorario==null) return new CitaResponse();
        cita.setHorario(newhorario);
        cita.setFecha(newhorario.getDia().getFecha());
        cita.setHoraInicio(newhorario.getHoras().getHoraInicio());
        cita.setHoraFin(newhorario.getHoras().getHoraFin());
        cita.setEstado(citaRequest.getEstado());
        newhorario.setEstado("reservado");
        
        cita = citaRepository.save(cita);
        CitaResponse citaResponse = CitaResponse.fromEntity(cita);
        return citaResponse;
        
    }

    public void deleteCitaById(String id) {
        citaRepository.deleteById(id);
    }
   
}
