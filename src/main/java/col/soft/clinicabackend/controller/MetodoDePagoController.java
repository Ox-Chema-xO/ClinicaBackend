
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.MetodoDePago;
import col.soft.clinicabackend.service.MetodoDePagoService;
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
@RequestMapping(path="api/v1/metododepago")
public class MetodoDePagoController {
    
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    MetodoDePagoService metodoDePagoService;
    
    @GetMapping()
    public ResponseEntity<?> getMetodosDePago(){
        List<MetodoDePago> listaMetodosDePago = null;
        try {
            listaMetodosDePago = metodoDePagoService.getMetodosDePago();
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaMetodosDePago.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Metodos de Pago not found").build());
        return ResponseEntity.ok(listaMetodosDePago);
    }
}
