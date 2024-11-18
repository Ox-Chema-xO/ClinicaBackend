package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.Especialidad;
import col.soft.clinicabackend.service.EspecialidadService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/especialidad")
public class EspecialidadController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    EspecialidadService especialidadService;    
    
    @GetMapping()
    public ResponseEntity<?> getEspecialidades(){
        List<Especialidad> listaEspecialidades=null;
        try{
            listaEspecialidades = especialidadService.getEspecialidades();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaEspecialidades.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Especialidades not found").build());
        return ResponseEntity.ok(listaEspecialidades);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertEspecialidad(@RequestBody Especialidad especialidad){        
        logger.info(">insertEspecialidad",especialidad.toString());
        Especialidad newEspecialidad;
        try{
            newEspecialidad=especialidadService.insertEspecialidad(especialidad);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newEspecialidad==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Especialidad not insert").build());
        return ResponseEntity.ok(newEspecialidad);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspecialidadById(@PathVariable String id){ 
        Optional<Especialidad> especialidad = Optional.empty();  
        try{
            especialidad=especialidadService.findEspecialidadById(id);
            if(!especialidad.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Especialidad not found for delete").build());
            }
            especialidadService.deleteEspecialidad(id);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        return ResponseEntity.ok(especialidad.get());       
    }
}
