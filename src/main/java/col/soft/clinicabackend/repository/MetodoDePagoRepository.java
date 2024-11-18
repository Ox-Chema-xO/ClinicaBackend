
package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago,Integer>{
    
}
