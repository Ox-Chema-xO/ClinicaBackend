
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.dto.OrdenDePagoRequest;
import col.soft.clinicabackend.dto.OrdenDePagoResponse;
import col.soft.clinicabackend.model.Cita;
import col.soft.clinicabackend.model.ComprobanteDePago;
import col.soft.clinicabackend.model.MetodoDePago;
import col.soft.clinicabackend.model.OrdenDePago;
import col.soft.clinicabackend.repository.CitaRepository;
import col.soft.clinicabackend.repository.ComprobanteDePagoRepository;
import col.soft.clinicabackend.repository.MetodoDePagoRepository;
import col.soft.clinicabackend.repository.OrdenDePagoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenDePagoService {
    @Autowired
    OrdenDePagoRepository ordenDePagoRepository;
    
    @Autowired
    CitaRepository citaRepository;
    
    @Autowired
    ComprobanteDePagoRepository comprobanteDePagoRepository;
    
    @Autowired
    MetodoDePagoRepository metodoDePagoRepository;
    
    public List<OrdenDePagoResponse> getOrdenesDePago(){
        return OrdenDePagoResponse.fromEntities(ordenDePagoRepository.findAll());
    }
    
    public OrdenDePagoResponse getOrdenDePagoById(String idOrdenDePago){
        return OrdenDePagoResponse.fromEntity(ordenDePagoRepository.findById(idOrdenDePago).get());
    }
    
    public OrdenDePagoResponse insertOrdenDePago(OrdenDePagoRequest ordenDePagoRequest){
        //verificar que cita existe
        String idCita = ordenDePagoRequest.getIdCita();
        Cita cita = citaRepository.findById(idCita).get();
        if(cita==null) return new OrdenDePagoResponse();
        
        OrdenDePago ordenDePago = new OrdenDePago(
                ordenDePagoRequest.getIdOrdenDePago(), //idOrdenDePago
                cita, //cita
                null, //comprobanteDePago
                null, //metodoDePago
                ordenDePagoRequest.getFecha(), //fecha
                cita.getHorario().getMedico().getEspecialidad().getCostoServicio(), //monto
                "noPagado" //estado
        ); 
        ordenDePago = ordenDePagoRepository.save(ordenDePago);
        OrdenDePagoResponse ordenDePagoResponse = OrdenDePagoResponse.fromEntity(ordenDePago);
        return ordenDePagoResponse;
    }
    
    public OrdenDePagoResponse updateOrdenDePagoComprobanteDePago(String idOrdenDePago, Integer idComprobanteDePago){
        //verirfica si existe la orden de pago
        OrdenDePago ordenDePago = ordenDePagoRepository.findById(idOrdenDePago).get();
        if(ordenDePago==null) return new OrdenDePagoResponse();
        //verifica si existe el comprobante de pago
        ComprobanteDePago comprobanteDePago = comprobanteDePagoRepository.findById(idComprobanteDePago).get();
        if(comprobanteDePago==null) return new OrdenDePagoResponse();
        //actualizamos el comprobante de pago
        ordenDePago.setComprobanteDePago(comprobanteDePago);
        //guardamos los cambios
        ordenDePago = ordenDePagoRepository.save(ordenDePago);
        OrdenDePagoResponse ordenDePagoResponse = OrdenDePagoResponse.fromEntity(ordenDePago);
        return ordenDePagoResponse;
    }
    
    public OrdenDePagoResponse updateOrdenDePagoMetodoDePago(String idOrdenDePago, Integer idMetodoDePago){
        //verirfica si existe la orden de pago
        OrdenDePago ordenDePago = ordenDePagoRepository.findById(idOrdenDePago).get();
        if(ordenDePago==null) return new OrdenDePagoResponse();
        //verificar si existe el metodo de pago
        MetodoDePago metodoDePago = metodoDePagoRepository.findById(idMetodoDePago).get();
        if(metodoDePago==null) return new OrdenDePagoResponse();
        
        ordenDePago.setMetodoDePago(metodoDePago);
        
        ordenDePago = ordenDePagoRepository.save(ordenDePago);
        OrdenDePagoResponse ordenDePagoResponse = OrdenDePagoResponse.fromEntity(ordenDePago);
        return ordenDePagoResponse;
    }
    
    public OrdenDePagoResponse updateOrdenDePagoEstado(String idOrdenDePago){
        //verirfica si existe la orden de pago
        OrdenDePago ordenDePago = ordenDePagoRepository.findById(idOrdenDePago).get();
        if(ordenDePago==null) return new OrdenDePagoResponse();
        //cambiamos el estado
        ordenDePago.setEstado("pagado");
        //guardamos los cambios
        ordenDePago = ordenDePagoRepository.save(ordenDePago);
        OrdenDePagoResponse ordenDePagoResponse = OrdenDePagoResponse.fromEntity(ordenDePago);
        return ordenDePagoResponse;
    }
    
    public void deleteOrdenDePago(String idOrdenDePago){
        ordenDePagoRepository.deleteById(idOrdenDePago);
    }
    
}
