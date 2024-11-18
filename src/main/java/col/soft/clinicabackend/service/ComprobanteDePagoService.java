
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.ComprobanteDePago;
import col.soft.clinicabackend.repository.ComprobanteDePagoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteDePagoService {
    @Autowired
    ComprobanteDePagoRepository comprobanteDePagoRepository;
    
    public List<ComprobanteDePago> getComprobantesDePago(){
        return comprobanteDePagoRepository.findAll();
    }
    
    public Optional<ComprobanteDePago> getComprobanteDePagoById(Integer idComprobanteDePago){
        return comprobanteDePagoRepository.findById(idComprobanteDePago);
    }
    
}
