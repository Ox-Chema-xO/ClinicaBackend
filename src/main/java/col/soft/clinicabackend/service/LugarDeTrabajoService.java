
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.LugarDeTrabajo;
import col.soft.clinicabackend.repository.LugarDeTrabajoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LugarDeTrabajoService {
    @Autowired
    LugarDeTrabajoRepository lugarDeTrabajoRepository;
    
    public List<LugarDeTrabajo> getLugaresDeTrabajo(){
        return lugarDeTrabajoRepository.findAll();
    }
    
    public Optional<LugarDeTrabajo> findLugarDeTrabajoById(Integer idLugarDeTrabajo){
        return lugarDeTrabajoRepository.findById(idLugarDeTrabajo);
    }
    
    public LugarDeTrabajo insertLugarDeTrabajo(LugarDeTrabajo lugarDeTrabajo){
        return lugarDeTrabajoRepository.save(lugarDeTrabajo);
    }
    
    public LugarDeTrabajo updateLugarDeTrabajo(LugarDeTrabajo lugarDeTrabajo){
        LugarDeTrabajo lugarDeTrabajoActualizado = null;
        lugarDeTrabajoActualizado = lugarDeTrabajoRepository.findById(lugarDeTrabajo.getIdLugarDeTrabajo()).get();
        //verifica si existe el cargo en la db
        if(lugarDeTrabajoActualizado==null) return new LugarDeTrabajo();
        //actualiza
        if(lugarDeTrabajo.getNombreLugar()!=null) lugarDeTrabajoActualizado.setNombreLugar(lugarDeTrabajo.getNombreLugar());
        
        return lugarDeTrabajoRepository.save(lugarDeTrabajoActualizado);
    }
    
    public void deleteLugarDeTrabajo(Integer idLugarDeTrabajo){
        lugarDeTrabajoRepository.deleteById(idLugarDeTrabajo);
    }
}
