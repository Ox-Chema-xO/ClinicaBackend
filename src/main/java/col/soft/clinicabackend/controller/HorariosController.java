package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.HorariosResponse;
import col.soft.clinicabackend.service.HorariosService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.sql.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/horarios")
public class HorariosController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    HorariosService horariosService;    
    
    @GetMapping()
    public ResponseEntity<?> getHorarios(){
        List<HorariosResponse> listaHorariosResponse=null;
        try{
            listaHorariosResponse=horariosService.getHorarios();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaHorariosResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Horarios not found").build());
        return ResponseEntity.ok(listaHorariosResponse);
    }
    
    
    @GetMapping("/find/by/fecha-and-estado")   
    public ResponseEntity<?> getHorariosDisponiblesByFecha(@RequestParam Date fecha, @RequestParam String estado){
        List<HorariosResponse> listaHorariosResponse=null;
        try{
            listaHorariosResponse=horariosService.getHorariosDisponiblesByFecha(fecha,estado);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaHorariosResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Horarios not found").build());
        return ResponseEntity.ok(listaHorariosResponse);
    }
    
    
}