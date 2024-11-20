
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.Cargo;
import col.soft.clinicabackend.model.LugarDeTrabajo;
import col.soft.clinicabackend.service.LugarDeTrabajoService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/lugardetrabajo")
public class LugarDeTrabajoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    LugarDeTrabajoService lugarDeTrabajoService;
    
    @GetMapping()
    public ResponseEntity<?> getLugaresDeTrabajo(){
        List<LugarDeTrabajo> listaLugaresDeTrabajo = null;
        try {
            listaLugaresDeTrabajo = lugarDeTrabajoService.getLugaresDeTrabajo();
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaLugaresDeTrabajo.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Lugares De Trabajo not found").build());
        return ResponseEntity.ok(listaLugaresDeTrabajo);
    }
    
    @GetMapping("/find/by/id")
    public ResponseEntity<?> findLugarDeTrabajoById(@RequestBody Optional<LugarDeTrabajo> lugarDeTrabajo){
        logger.info(">findLugarDeTrabajoById" + lugarDeTrabajo.toString());
        try {
            lugarDeTrabajo = lugarDeTrabajoService.findLugarDeTrabajoById(lugarDeTrabajo.get().getIdLugarDeTrabajo());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(lugarDeTrabajo == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Lugar de Trabajo not found").build());
        return ResponseEntity.ok(lugarDeTrabajo);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertLugarDeTrabajo(@RequestBody Optional<LugarDeTrabajo> lugarDeTrabajo){
        logger.info(">insertLugarDeTrabajo" + lugarDeTrabajo.toString());
        LugarDeTrabajo newlugarDeTrabajo = null;
        try {
            newlugarDeTrabajo = lugarDeTrabajoService.insertLugarDeTrabajo(lugarDeTrabajo.get());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newlugarDeTrabajo==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Lugar de Trabajo not insert").build());
        return ResponseEntity.ok(newlugarDeTrabajo);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateLugarDeTrabajo(@RequestBody Optional<LugarDeTrabajo> lugarDeTrabajo){
        logger.info(">updateLugarDeTrabajo" + lugarDeTrabajo.toString());
        LugarDeTrabajo updateLugarDeTrabajo = null;
        try {
            updateLugarDeTrabajo = lugarDeTrabajoService.updateLugarDeTrabajo(lugarDeTrabajo.get());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(updateLugarDeTrabajo == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Lugar de Trabajo not update").build());
        return ResponseEntity.ok(updateLugarDeTrabajo);
    }
    
    @DeleteMapping()
    public ResponseEntity<?> deleteLugarDeTrabajo(@RequestBody Optional<LugarDeTrabajo> lugarDeTrabajo){
        logger.info(">deleteLugarDeTrabajo" + lugarDeTrabajo.toString());
        LugarDeTrabajo deleteLugarDeTrabajo = null;
        try {
            deleteLugarDeTrabajo = lugarDeTrabajoService.findLugarDeTrabajoById(lugarDeTrabajo.get().getIdLugarDeTrabajo()).get();
            if(deleteLugarDeTrabajo==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Lugar de Trabajo not found for delete").build());
            }
            lugarDeTrabajoService.deleteLugarDeTrabajo(lugarDeTrabajo.get().getIdLugarDeTrabajo());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(deleteLugarDeTrabajo);
    }
    
}
