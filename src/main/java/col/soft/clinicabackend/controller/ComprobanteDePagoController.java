
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.model.ComprobanteDePago;
import col.soft.clinicabackend.service.ComprobanteDePagoService;
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
@RequestMapping(path="api/v1/comprobantedepago")
public class ComprobanteDePagoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    ComprobanteDePagoService comprobanteDePagoService;
    @GetMapping()
    public ResponseEntity<?> getComprobantesDePago(){
        List<ComprobanteDePago> listaComprobantesDePago = null;
        try {
            listaComprobantesDePago = comprobanteDePagoService.getComprobantesDePago();
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaComprobantesDePago.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Comprobantes de Pago not found").build());
        return ResponseEntity.ok(listaComprobantesDePago);
    }
    
}
