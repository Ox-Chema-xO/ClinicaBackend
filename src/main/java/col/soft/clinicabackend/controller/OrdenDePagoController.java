
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.OrdenDePagoRequest;
import col.soft.clinicabackend.dto.OrdenDePagoResponse;
import col.soft.clinicabackend.service.OrdenDePagoService;
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
@RequestMapping(path="api/v1/ordendepago")
public class OrdenDePagoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    OrdenDePagoService ordenDePagoService;
    
    @GetMapping()
    public ResponseEntity<?> getOrdenesDePago(){
        logger.info(">listar ordenes de pago");
        List<OrdenDePagoResponse> listaOrdenesDePagoResponse = null;
        try {
            listaOrdenesDePagoResponse = ordenDePagoService.getOrdenesDePago();
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaOrdenesDePagoResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ordenes de pago not found").build());
        return ResponseEntity.ok(listaOrdenesDePagoResponse);
    }
    
    @GetMapping("/find/by/id")
    public ResponseEntity<?> getOrdenDePagoById(@RequestParam String idOrdenDePago){
        logger.info(">find by id");
        OrdenDePagoResponse ordenDePagoResponse = null;
        try {
            ordenDePagoResponse = ordenDePagoService.getOrdenDePagoById(idOrdenDePago);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ordenDePagoResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Orden de pago not found").build());
        return ResponseEntity.ok(ordenDePagoResponse);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertOrdenDePago(@RequestBody OrdenDePagoRequest ordenDePagoRequest){
        logger.info(">insert" + ordenDePagoRequest.toString());
        OrdenDePagoResponse ordenDePagoResponse;
        try {
            ordenDePagoResponse = ordenDePagoService.insertOrdenDePago(ordenDePagoRequest);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ordenDePagoResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Orden de pago not insert").build());
        return ResponseEntity.ok(ordenDePagoResponse);
    }
    
    @PutMapping("/update/comprobantedepago")
    public ResponseEntity<?> updateOrdenDePagoComprobanteDePago(@RequestParam String idOrdenDePago, @RequestParam Integer idComprobanteDePago){
        logger.info(">update comprobante de pago");
        OrdenDePagoResponse ordenDePagoResponse = null;
        try {
            ordenDePagoResponse = ordenDePagoService.updateOrdenDePagoComprobanteDePago(idOrdenDePago, idComprobanteDePago);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ordenDePagoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Comprobante de Pago from Orden de pago not update").build());
        return ResponseEntity.ok(ordenDePagoResponse);
    }
    
    @PutMapping("/update/metododepago")
    public ResponseEntity<?> updateOrdenDePagoMetodoDePago(@RequestParam String idOrdenDePago, @RequestParam Integer idMetodoDePago){
        logger.info(">update metodo de pago");
        OrdenDePagoResponse ordenDePagoResponse = null;
        try {
            ordenDePagoResponse = ordenDePagoService.updateOrdenDePagoMetodoDePago(idOrdenDePago, idMetodoDePago);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ordenDePagoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Metodo de Pago from Orden de pago not update").build());
        return ResponseEntity.ok(ordenDePagoResponse);
    }
    
    @PutMapping("/update/estado")
    public ResponseEntity<?> updateOrdenDePagoEstado(@RequestParam String idOrdenDePago){
        logger.info(">update estado");
        OrdenDePagoResponse ordenDePagoResponse = null;
        try {
            ordenDePagoResponse = ordenDePagoService.updateOrdenDePagoEstado(idOrdenDePago);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ordenDePagoResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Estado from Orden de pago not update").build());
        return ResponseEntity.ok(ordenDePagoResponse);
    }
    
    @DeleteMapping()
    public ResponseEntity<?> deleteOrdenDePago(@RequestParam String idOrdenDePago){
        logger.info(">delete orden de pago");
        OrdenDePagoResponse ordenDePagoResponse = null;
        try {
            //buscamos si existe esta orden de pago
            ordenDePagoResponse=ordenDePagoService.getOrdenDePagoById(idOrdenDePago);
            if(ordenDePagoResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Orden de pago not found for delete").build());
            ordenDePagoService.deleteOrdenDePago(idOrdenDePago);
        } catch (Exception e) {
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(ordenDePagoResponse);
    }
}
