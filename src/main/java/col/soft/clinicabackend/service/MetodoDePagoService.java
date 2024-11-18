
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.MetodoDePago;
import col.soft.clinicabackend.repository.MetodoDePagoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoDePagoService {
    @Autowired
    MetodoDePagoRepository metodoDePagoRepository;
    
    public List<MetodoDePago> getMetodosDePago(){
        return metodoDePagoRepository.findAll();
    }
    
    public Optional<MetodoDePago> getMetodoDePagoById(Integer idMetodoDePago){
        return metodoDePagoRepository.findById(idMetodoDePago);
    }
    
}
