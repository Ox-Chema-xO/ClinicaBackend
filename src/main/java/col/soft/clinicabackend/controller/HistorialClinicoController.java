package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.HistorialClinicoRequest;
import col.soft.clinicabackend.dto.HistorialClinicoResponse;
import col.soft.clinicabackend.service.HistorialClinicoService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/historialclinico")
public class HistorialClinicoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    HistorialClinicoService historialClinicoService;    
    
    @GetMapping()
    public ResponseEntity<?> getHistorialesClinicos(){
        List<HistorialClinicoResponse> listaHistorialClinicoResponse=null;
        try{
            listaHistorialClinicoResponse=historialClinicoService.getHistorialesClinicos();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaHistorialClinicoResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Historiales Clinicos not found").build());
        return ResponseEntity.ok(listaHistorialClinicoResponse);
    }
        
    @GetMapping("/{dni}")
    public ResponseEntity<?> findHistorialClinicoByPacienteDni(@PathVariable Long dni){ 
        HistorialClinicoResponse historialClinicoResponse;
        try {  
            historialClinicoResponse = historialClinicoService.findHistorialClinicoByPacienteDni(dni);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (historialClinicoResponse==null) {    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Historial Clinico not found").build());
        }
          
        return ResponseEntity.ok(historialClinicoResponse);  

    }
    
    @GetMapping("/find/{id}")   
    public ResponseEntity<?> findHistorialClinicoById(@PathVariable String id){        
        HistorialClinicoResponse historialClinicoResponse;
        try {  
            historialClinicoResponse = historialClinicoService.findHistorialClinicoById(id);  
        } catch(Exception e) {  
            logger.error("Error inesperado", e);  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }  

        if (historialClinicoResponse==null) {    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Historial Clinico not found").build());
        }
          
        return ResponseEntity.ok(historialClinicoResponse);  
    }
    
    @PostMapping()
    public ResponseEntity<?> insertHistorialClinico(@RequestBody HistorialClinicoRequest historialClinicoRequest){
        logger.info(">insert"+ historialClinicoRequest.toString());
        HistorialClinicoResponse historialClinicoResponse;
        try{
            historialClinicoResponse=historialClinicoService.insertHistorialClinico(historialClinicoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(historialClinicoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Historial Clinico not insert").build());
        return ResponseEntity.ok(historialClinicoResponse);
    }
    
    @PutMapping("/{dni}")
    public ResponseEntity<?> updateHistorialClinico(@PathVariable Long dni,@RequestBody HistorialClinicoRequest historialClinicoRequest){
        logger.info(">update"+ historialClinicoRequest.toString());
        HistorialClinicoResponse historialClinicoResponse;
        try{
            historialClinicoResponse=historialClinicoService.updateHistorialClinico(dni,historialClinicoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(historialClinicoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Historial Clinico not insert").build());
        return ResponseEntity.ok(historialClinicoResponse);
    }

}
