
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.model.Cargo;
import col.soft.clinicabackend.repository.CargoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    @Autowired
    CargoRepository cargoRepository;
    
    public List<Cargo> getCargos(){
        return cargoRepository.findAll();
    }
    
    public Optional<Cargo> findCargoById(Integer idCargo){
        return cargoRepository.findById(idCargo);
    }
    
    public Cargo insertCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }
    
    public Cargo updateCargo(Cargo cargo){
        Cargo cargoActualizado = null;
        cargoActualizado = cargoRepository.findById(cargo.getIdCargo()).get();
        //verifica si existe el cargo en la db
        if(cargoActualizado==null) return new Cargo();
        //actualiza
        if(cargo.getNombreCargo()!=null) cargoActualizado.setNombreCargo(cargo.getNombreCargo());
        if(cargo.getSalario()!=null) cargoActualizado.setSalario(cargo.getSalario());
        
        return cargoRepository.save(cargoActualizado);
    }
    
    public void deleteCargo(Integer idCargo){
        cargoRepository.deleteById(idCargo);
    }
}
