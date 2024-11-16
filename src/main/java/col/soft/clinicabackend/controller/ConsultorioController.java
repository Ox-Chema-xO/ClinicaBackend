package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.Consultorio;
import col.soft.clinicabackend.service.ConsultorioService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/consultorio")
public class ConsultorioController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    ConsultorioService consultorioService;    
    
    @GetMapping()
    public ResponseEntity<?> getConsultorios(){
        List<Consultorio> listaConsultorios=null;
        try{
            listaConsultorios=consultorioService.getConsultorios();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaConsultorios.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Consultorios not found").build());
        return ResponseEntity.ok(listaConsultorios);
    }
}
