
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.Cargo;
import col.soft.clinicabackend.service.CargoService;
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
@RequestMapping(path="api/v1/cargo")
public class CargoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    CargoService cargoService;
    
    @GetMapping("/listar")
    public ResponseEntity<?> getCargos(){
        List<Cargo> listaCargos = null;
        try {
            listaCargos = cargoService.getCargos();
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaCargos.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cargos not found").build());
        return ResponseEntity.ok(listaCargos);
    }
    
    @GetMapping("/find/by/id")
    public ResponseEntity<?> findCargoById(@RequestBody Optional<Cargo> cargo){
        logger.info(">findCargoById" + cargo.toString());
        try {
            cargo = cargoService.findCargoById(cargo.get().getIdCargo());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(cargo == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cargo not found").build());
        return ResponseEntity.ok(cargo);
    }    
    
    @PostMapping()
    public ResponseEntity<?> insertCargo(@RequestBody Optional<Cargo> cargo){
        logger.info(">insertCargo" + cargo.toString());
        Cargo newCargo = null;
        try {
            newCargo = cargoService.insertCargo(cargo.get());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newCargo==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cargo not insert").build());
        return ResponseEntity.ok(newCargo);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateCargo(@RequestBody Optional<Cargo> cargo){
        logger.info(">updateCargo" + cargo.toString());
        Cargo updateCargo = null;
        try {
            updateCargo = cargoService.updateCargo(cargo.get());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(updateCargo == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cargo not update").build());
        return ResponseEntity.ok(updateCargo);
    }
    
    @DeleteMapping()
    public ResponseEntity<?> deleteCargo(@RequestBody Optional<Cargo> cargo){
        logger.info(">deleteCargo" + cargo.toString());
        Cargo deleteCargo = null;
        try {
            deleteCargo = cargoService.findCargoById(cargo.get().getIdCargo()).get();
            if(deleteCargo==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cargo not found for delete").build());
            }
            cargoService.deleteCargo(cargo.get().getIdCargo());
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(deleteCargo);
    }
}
