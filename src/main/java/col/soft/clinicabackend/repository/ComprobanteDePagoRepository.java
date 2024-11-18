
package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.ComprobanteDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ComprobanteDePagoRepository extends JpaRepository<ComprobanteDePago,Integer>{
    
}
