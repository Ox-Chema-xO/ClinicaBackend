
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.EmpleadoRequest;
import col.soft.clinicabackend.dto.EmpleadoResponse;
import col.soft.clinicabackend.service.EmpleadoService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/empleado")
public class EmpleadoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    EmpleadoService empleadoService;
    
    @GetMapping()
    public ResponseEntity<?> getEmpleados(){
        List<EmpleadoResponse> listaEmpleadosResponse=null;
        try{
            listaEmpleadosResponse=empleadoService.getEmpleados();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaEmpleadosResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Empleados not found").build());
        return ResponseEntity.ok(listaEmpleadosResponse);
    }
    
    @GetMapping("/find/by/dni")   
    public ResponseEntity<?> findEmpleadoById(@RequestParam Integer dni){
        logger.info(">findById");
        EmpleadoResponse empleadoResponse = null;
        try{
            empleadoResponse = empleadoService.getEmpleadoByDni(dni);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empleadoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Empleado not found").build());
        return ResponseEntity.ok(empleadoResponse);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertEmpleado(@RequestBody EmpleadoRequest empleadoRequest){
        logger.info(">insert"+ empleadoRequest.toString());
        EmpleadoResponse empleadoResponse = null;
        try{
            empleadoResponse = empleadoService.insertEmpleado(empleadoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empleadoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Empleado not insert").build());
        return ResponseEntity.ok(empleadoResponse);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateEmpleado(@RequestBody Optional<EmpleadoRequest> empleadoRequest){
        logger.info(">update"+ empleadoRequest.toString());
        EmpleadoResponse empleadoResponse = null;
        try{
            empleadoResponse = empleadoService.updateEmpleado(empleadoRequest.get());
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empleadoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Empleado not update").build());
        return ResponseEntity.ok(empleadoResponse);
    }
    
    @DeleteMapping()
    public ResponseEntity<?> deleteEmpleadoById(@RequestParam Integer dni){ 
        EmpleadoResponse empleadoResponse = null;
        try{
            empleadoResponse = empleadoService.getEmpleadoByDni(dni);
            if(empleadoResponse==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Empleado not found for delete").build());
            }
            empleadoService.deleteEmpleado(dni);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        return ResponseEntity.ok(empleadoResponse);       
    }
}
