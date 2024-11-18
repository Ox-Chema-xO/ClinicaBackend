package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.Paciente;
import col.soft.clinicabackend.service.PacienteService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/paciente")
public class PacienteController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PacienteService pacienteService;    
    
    @GetMapping()
    public ResponseEntity<?> getPacientes(){
        List<Paciente> listaPacientes=null;
        try{
            listaPacientes=pacienteService.getPacientes();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaPacientes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Pacientes not found").build());
        return ResponseEntity.ok(listaPacientes);
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findPacienteByDni(@RequestBody Optional<Paciente> paciente){        
        logger.info(">findPacienteByDni",paciente.get().toString());
        try{
            paciente=pacienteService.findPacienteByDni(paciente.get().getDni());
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(paciente==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Paciente not found").build());
        return ResponseEntity.ok(paciente.get());
    }
    
    @GetMapping("/{dni}")   
    public ResponseEntity<?> findPacienteByDniV2(@PathVariable Long dni){        
        Optional<Paciente> paciente = Optional.empty();  
        try {  
            paciente = pacienteService.findPacienteByDni(dni);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (paciente.isPresent()) {  
            return ResponseEntity.ok(paciente.get());  
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Paciente not found").build());  
        
    }
    
    @PostMapping()
    public ResponseEntity<?> insertPaciente(@RequestBody Paciente paciente){        
        logger.info(">insertPaciente",paciente.toString());
        Paciente newPaciente;
        try{
            newPaciente=pacienteService.insertPaciente(paciente);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newPaciente==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());
        return ResponseEntity.ok(newPaciente);
    }
    
    @PutMapping("/{dni}")
    public ResponseEntity<?> updatePacienteByDni(@PathVariable Long dni,@RequestBody Paciente paciente){        
        logger.info(">updatePaciente",paciente.toString());
        Paciente newPaciente;
        try{
            newPaciente=pacienteService.updatePacienteByDni(dni,paciente);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newPaciente==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Paciente not update").build());
        return ResponseEntity.ok(newPaciente);
    }
}