package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.MedicoRequest;
import col.soft.clinicabackend.dto.MedicoResponse;
import col.soft.clinicabackend.service.MedicoService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/medico")
public class MedicoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    MedicoService medicoService;    
    
    @GetMapping()
    public ResponseEntity<?> getMedicos(){
        List<MedicoResponse> listaMedicoResponse=null;
        try{
            listaMedicoResponse=medicoService.getMedicos();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaMedicoResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medicos not found").build());
        return ResponseEntity.ok(listaMedicoResponse);
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<?> findMedicoById(@PathVariable String id){ 
        MedicoResponse medicoResponse;
        try {  
            medicoResponse = medicoService.findMedicoById(id);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (medicoResponse==null) {    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medico not found").build());
        }
          
        return ResponseEntity.ok(medicoResponse);  

    }
    
    @GetMapping("/find/by/nameEspecialidad")   
    public ResponseEntity<?> findMedicoByEspecialidad(@RequestParam String nameEspecialidad){        
        List<MedicoResponse> listaMedicoResponse=null;
        try {  
            listaMedicoResponse = medicoService.findByEspecialidadNombre(nameEspecialidad);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (listaMedicoResponse==null) {    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medicos not found").build());
        }
          
        return ResponseEntity.ok(listaMedicoResponse);  
    }
    
    @GetMapping("/find/by/nameConsultorio")   
    public ResponseEntity<?> findMedicoByConsultorio(@RequestParam String nameConsultorio){        
        List<MedicoResponse> listaMedicoResponse=null;
        try {  
            listaMedicoResponse = medicoService.findByConsultorioNombre(nameConsultorio);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (listaMedicoResponse==null) {    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medicos not found").build());
        }
          
        return ResponseEntity.ok(listaMedicoResponse);  
    }
    
    @PostMapping()
    public ResponseEntity<?> insertMedico(@RequestBody MedicoRequest medicoRequest){
        logger.info(">insert"+ medicoRequest.toString());
        MedicoResponse medicoResponse;
        try{
            medicoResponse=medicoService.insertMedico(medicoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(medicoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medico not insert").build());
        return ResponseEntity.ok(medicoResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicoById(@PathVariable String id,@RequestBody MedicoRequest medicoRequest){
        logger.info(">update"+ medicoRequest.toString());
        MedicoResponse medicoResponse;
        try{
            medicoResponse=medicoService.updateMedico(id,medicoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(medicoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medico not update").build());
        return ResponseEntity.ok(medicoResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicoById(@PathVariable String id){ 
        MedicoResponse medicoResponse;
        try{
            medicoResponse = medicoService.findMedicoById(id); 
            if(medicoResponse==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Medico not found for delete").build());
            }
            medicoService.deleteMedicoById(id);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        return ResponseEntity.ok(medicoResponse);       
    }
    
    
    
    
}
