package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.CitaRequest;
import col.soft.clinicabackend.dto.CitaResponse;
import col.soft.clinicabackend.service.CitaService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.sql.Date;
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
@RequestMapping(path="api/v1/cita")
public class CitaController {
    
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    CitaService citaService;    
    
    @GetMapping()
    public ResponseEntity<?> getCitas(){
        List<CitaResponse> listaCitaResponse=null;
        try{
            listaCitaResponse=citaService.getCitas();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaCitaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Citas not found").build());
        return ResponseEntity.ok(listaCitaResponse);
    }
    
    @GetMapping("/find/by/id/{id}")
    public ResponseEntity<?> getCitaById(@PathVariable String id){
        CitaResponse citaResponse;
        try{
            citaResponse=citaService.getCitaById(id);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(citaResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Citas not found").build());
        return ResponseEntity.ok(citaResponse);
    }
   
    @GetMapping("/find/by/fecha-and-estado")   
    public ResponseEntity<?> getCitasEstadosByFecha(@RequestParam Date fecha, @RequestParam String estado){
        List<CitaResponse> listaCitasResponse=null;
        try{
            listaCitasResponse=citaService.getCitasEstadosByFecha(fecha,estado);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaCitasResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Citas not found").build());
        return ResponseEntity.ok(listaCitasResponse);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertCita(@RequestBody CitaRequest citaRequest){
        logger.info(">insert"+ citaRequest.toString());
        CitaResponse citaResponse;
        try{
            citaResponse=citaService.insertCita(citaRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(citaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cita not insert").build());
        return ResponseEntity.ok(citaResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCitaById(@PathVariable String id,@RequestBody CitaRequest citaRequest){
        logger.info(">update"+ citaRequest.toString());
        CitaResponse citaResponse;
        try{
            citaResponse=citaService.updateCita(id,citaRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(citaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cita not update").build());
        return ResponseEntity.ok(citaResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCitaById(@PathVariable String id){ 
        CitaResponse citaResponse;
        try{
            citaResponse = citaService.getCitaById(id);
            if(citaResponse==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cita not found for delete").build());
            }
            citaService.deleteCitaById(id);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        return ResponseEntity.ok(citaResponse);       
    }
    
    
    
    
}
